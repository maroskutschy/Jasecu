package Utils.KarateReportPortal;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.intuit.karate.Results;
import com.intuit.karate.core.*;
import io.reactivex.Maybe;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import rp.com.google.common.base.Strings;
import rp.com.google.common.base.Supplier;
import rp.com.google.common.base.Suppliers;

import java.util.*;

import static rp.com.google.common.base.Strings.isNullOrEmpty;

public class ScenarioReporter {

    private static final String COLON_INFIX = ": ";
    private static final String SKIPPED_ISSUE_KEY = "skippedIssue";
    /* Initialize the logger */
    private static final Logger LOGGER = LogHelper.getLogger();
    private static final String INFO_LEVEL = "INFO";
    private static final String ERROR_LEVEL = "ERROR";
    private static final String PASSED = "passed";
    private static final String FAILED = "failed";
    private static final Map<String, Date> featureStartDateMap = Collections.synchronizedMap(new HashMap<String, Date>());
    private static ScenarioReporter scenarioReporter;
    private static Supplier<Launch> launch;
    private static String featureNamePrefix;

    /**
     * Start launch.
     */
    public static void startLaunch() {

        launch = Suppliers.memoize(new Supplier<Launch>() {
            @Override
            public Launch get() {
                final ReportPortal reportPortal = ReportPortal.builder().build();
                ListenerParameters parameters = reportPortal.getParameters();
                StartLaunchRQ rq = buildStartLaunchRq(reportPortal.getParameters());
                return reportPortal.newLaunch(rq);
            }
        });

        launch.get().start();
    }

    /**
     * Build stop feature rq finish test item rq.
     *
     * @param featureResult the feature result
     * @return the finish test item rq
     */
    public static FinishTestItemRQ buildStopFeatureRq(FeatureResult featureResult) {
        Date now = Calendar.getInstance().getTime();
        FinishTestItemRQ rq = new FinishTestItemRQ();
        rq.setEndTime(now);
        rq.setStatus(getFeatureStatus(featureResult));
        return rq;
    }

    /**
     * Build start feature rq start test item rq.
     *
     * @param featureResult the feature result
     * @return the start test item rq
     */
    public static StartTestItemRQ buildStartFeatureRq(FeatureResult featureResult) {
        Feature feature = featureResult.getFeature();
        StartTestItemRQ rq = new StartTestItemRQ();
        Maybe<String> root = getRootItemId();
        if (!Strings.isNullOrEmpty(feature.getName())) {
            rq.setDescription(feature.getName());
        }
        if (feature.getTags() != null && !feature.getTags().isEmpty()) {
            Set<ItemAttributesRQ> attributes = Utils.extractAttributes(feature.getTags());
            rq.setAttributes(attributes);
        }
        String featureUri = Utils.getURI(feature);
        rq.setName(FilenameUtils.getBaseName(featureUri));
        if (featureStartDateMap.containsKey(featureUri)) {
            rq.setStartTime(featureStartDateMap.get(featureUri));
        } else {
            rq.setStartTime(Calendar.getInstance().getTime());
        }
        rq.setType("TEST");
        return rq;
    }

    /**
     * Build start launch rq start launch rq.
     *
     * @param parameters the parameters
     * @return the start launch rq
     */
    protected static StartLaunchRQ buildStartLaunchRq(ListenerParameters parameters) {

        StartLaunchRQ rq = new StartLaunchRQ();
        rq.setName(parameters.getLaunchName());
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setMode(parameters.getLaunchRunningMode());
        rq.setAttributes(parameters.getAttributes());
        rq.setDescription(parameters.getDescription());
        rq.setRerun(parameters.isRerun());
        if (!isNullOrEmpty(parameters.getRerunOf())) {
            rq.setRerunOf(parameters.getRerunOf());
        }
        if (null != parameters.getSkippedAnIssue()) {
            ItemAttributesRQ skippedIssueAttribute = new ItemAttributesRQ();
            skippedIssueAttribute.setKey(SKIPPED_ISSUE_KEY);
            skippedIssueAttribute.setValue(parameters.getSkippedAnIssue().toString());
            skippedIssueAttribute.setSystem(true);
            rq.getAttributes().add(skippedIssueAttribute);
        }
        return rq;
    }

    /**
     * Stop launch.
     *
     * @param results the results
     */
    public static void stopLaunch(Results results) {
        FinishExecutionRQ finishLaunchRq = new FinishExecutionRQ();
        finishLaunchRq.setEndTime(Calendar.getInstance().getTime());
        finishLaunchRq.setStatus(getLaunchStatus(results));
        launch.get().finish(finishLaunchRq);
    }

    /**
     * Gets root item id.
     *
     * @return the root item id
     */
    protected static Maybe<String> getRootItemId() {
        return null;
    }

    private static String getLaunchStatus(Results results) {
        StatusEnum status = StatusEnum.SKIPPED;
        if (results.getScenarioCount() > 0) {
            if (results.getFailCount() > 0) {
                status = StatusEnum.FAILED;
            } else {
                status = StatusEnum.PASSED;
            }
        }
        return status.toString();
    }

    private static String getFeatureStatus(FeatureResult featureResult) {
        StatusEnum status = StatusEnum.SKIPPED;
        if (featureResult.getScenarioCount() > 0) {
            if (featureResult.isFailed()) {
                status = StatusEnum.FAILED;
            } else {
                status = StatusEnum.PASSED;
            }
        }
        return status.toString();
    }

    private static String getScenarioStatus(ScenarioResult scenarioResult) {
        StatusEnum status = StatusEnum.SKIPPED;
        if (scenarioResult.getStepResults() != null && scenarioResult.getStepResults().size() > 0) {
            if (scenarioResult.getFailedStep() != null) {
                status = StatusEnum.FAILED;
            } else {
                status = StatusEnum.PASSED;
            }
        }
        return status.toString();
    }

    /**
     * Handle before feature.
     *
     * @param feature the feature
     */
    public static void handleBeforeFeature(Feature feature) {
        String featureUri = Utils.getURI(feature);
        String featureName = FilenameUtils.getBaseName(featureUri);
        if (!Strings.isNullOrEmpty(featureNamePrefix) && !featureName.startsWith(featureNamePrefix)) {
            LOGGER.warn("Dropping feature event due to feature name prefix mismatch featureName={} ", featureName);
        }
        featureStartDateMap.put(featureUri, Calendar.getInstance().getTime());

    }

    /**
     * Handle after feature.
     *
     * @param featureResult    the feature result
     * @param executionContext the execution context
     * @throws InterruptedException the interrupted exception
     */
    public static void handleAfterFeature(FeatureResult featureResult, ExecutionContext executionContext) throws InterruptedException {
        String featureUri = Utils.getURI(featureResult.getFeature());
        String featureName = FilenameUtils.getBaseName(featureUri);

        if (featureResult.getScenarioCount() <= 0) {
            LOGGER.warn("Dropping feature event as scenario count is zero featureName={} ", featureName);
            return;
        }

        if (!Strings.isNullOrEmpty(featureNamePrefix)) {
            if (!featureName.startsWith(featureNamePrefix)) {
                LOGGER.warn("Dropping feature event due to feature name prefix mismatch featureName={} ", featureName);
                return;
            }
        }
        StartTestItemRQ startTestItemRQ = buildStartFeatureRq(featureResult);
        Maybe<String> featureId = launch.get().startTestItem(getRootItemId(), startTestItemRQ);
        for (ScenarioResult scenarioResult : featureResult.getScenarioResults()) {
            Maybe<String> scenarioId = launch.get().startTestItem(featureId, buildStartScenarioRq(scenarioResult, executionContext));
            List<StepResult> stepResults = scenarioResult.getStepResults();
            int stepNumber = 1;
            for (StepResult stepResult : stepResults) {
                Thread.sleep(70);
                StepResult stepResultsToMap = stepResult;
                String status = stepResultsToMap.getResult().getStatus();
                String logLevel = PASSED.equals(status) ? INFO_LEVEL : ERROR_LEVEL;
                String logs = Utils.printStepResult(stepResultsToMap, stepNumber);
                if (stepResultsToMap.getCallResults() != null) {
                    StringBuilder callResults = callResultLog(stepResultsToMap.getCallResults());
                    logs += callResults;
                }
                stepNumber++;
                sendLog(logs, logLevel);
            }

            FinishTestItemRQ finishTestItemRQ = buildStopScenarioRq(scenarioResult, executionContext);
            launch.get().finishTestItem(scenarioId, finishTestItemRQ);

        }

        FinishTestItemRQ finishTestItemRQ = buildStopFeatureRq(featureResult);
        launch.get().finishTestItem(featureId, finishTestItemRQ);

    }

    /**
     * Build start scenario rq start test item rq.
     *
     * @param scenarioResult   the scenario result
     * @param executionContext the execution context
     * @return the start test item rq
     */
    protected static StartTestItemRQ buildStartScenarioRq(ScenarioResult scenarioResult, ExecutionContext executionContext) {
        StartTestItemRQ rq = new StartTestItemRQ();
        rq.setDescription(scenarioResult.getScenario().getDescription());
        rq.setName(scenarioResult.getScenario().getName());
        String featureUri = Utils.getURI(scenarioResult.getScenario().getFeature());
        if (featureStartDateMap.containsKey(featureUri)) {
            rq.setStartTime(new Date(scenarioResult.getStartTime() + featureStartDateMap.get(featureUri).getTime()));
        } else {
            rq.setStartTime(Calendar.getInstance().getTime());
        }
        rq.setType("STEP");
        return rq;
    }

    private static FinishTestItemRQ buildStopScenarioRq(ScenarioResult scenarioResult, ExecutionContext executionContext) {
        Date now = Calendar.getInstance().getTime();
        FinishTestItemRQ rq = new FinishTestItemRQ();
        String featureUri = Utils.getURI(scenarioResult.getScenario().getFeature());
        if (featureStartDateMap.containsKey(featureUri)) {
            rq.setEndTime(new Date(scenarioResult.getEndTime() + featureStartDateMap.get(featureUri).getTime()));
        } else {
            rq.setEndTime(Calendar.getInstance().getTime());
        }
        rq.setStatus(getScenarioStatus(scenarioResult));
        return rq;
    }

    private static void sendLog(final String message, final String level) {
        Utils.sendLog(message, level, null);
    }

    private static StringBuilder callResultLog(List<FeatureResult> callLog) {
        StringBuilder result = new StringBuilder();
        int n = 1;
        for (FeatureResult i : callLog) {
            for (ScenarioResult j : i.getScenarioResults()) {
                for (StepResult k : j.getStepResults()) {
                    String status = k.getResult().getStatus();
                    String logLevel = PASSED.equals(status) ? INFO_LEVEL : ERROR_LEVEL;
                    result.append(Utils.printCallStepResult(k, n));
                    n++;
                    if (k.getCallResults() != null) {
                        result.append(callResultLog(k.getCallResults()));
                    }
                }

            }
        }
        return result;
    }

    /**
     * Sets feature name prefix.
     *
     * @param featureNamePrefix the feature name prefix
     */
    public void setFeatureNamePrefix(String featureNamePrefix) {
        this.featureNamePrefix = featureNamePrefix;
    }

    private void reportErrorMessage(ScenarioResult scenarioResult, String itemUuid) {

        StringBuilder sb = new StringBuilder();
        if (!Strings.isNullOrEmpty(scenarioResult.getStepResults().toString())) {
            sb.append("  ****** Failure Message ******");
            sb.append("\n\n");
            sb.append(scenarioResult.getFailureMessageForDisplay());
            sb.append("\n\n");
            sb.append("  ****** End Failure Message ******");
            sb.append("\n\n");
        }
        for (StepResult stepResult : scenarioResult.getStepResults()) {
            if (!Strings.isNullOrEmpty(stepResult.getErrorMessage())) {
                sb.append("  ****** Error Message ******");
                sb.append("\n\n");
                sb.append(stepResult.getErrorMessage());
                sb.append("\n\n");
                sb.append("  ****** End Error Message ******");
                sb.append("\n\n");
            }
        }
        Utils.sendLog(sb.toString(), "ERROR", null);

    }
}
