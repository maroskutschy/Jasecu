package Utils.KarateReportPortal;

import com.intuit.karate.Results;
import com.intuit.karate.core.*;
import com.intuit.karate.http.HttpRequestBuilder;
import org.slf4j.Logger;

import static Utils.KarateReportPortal.ScenarioReporter.*;

/**
 * Reportportal Execution Hook.
 */
public class ReportPortalHook implements ExecutionHook {

    /* Initialize the logger */
    private static final Logger log = LogHelper.getLogger();

    @Override
    public boolean beforeScenario(Scenario scenario, ScenarioContext context) {
        return context.getCallDepth() <= 0; // only enforce changes for top-level scenarios (not called ones)
    }

    @Override
    public void afterScenario(ScenarioResult result, ScenarioContext context) {

    }

    @Override
    public boolean beforeFeature(Feature feature, ExecutionContext context) {
        try {

            if (!context.callContext.isCalled()) {
                handleBeforeFeature(feature);
            }

        } catch (Exception ex) {
            log.error("Error while handling before feature event: {}", ex.getMessage(), ex);
        }

        return true;
    }

    @Override
    public void afterFeature(FeatureResult result, ExecutionContext context) {

        try {

            if (!context.callContext.isCalled()) {
                handleAfterFeature(result, context);
            }
        } catch (Exception ex) {
            log.error("Error while handling after feature event: {}", ex.getMessage(), ex);
        }

    }

    @Override
    public void beforeAll(Results results) {

        try {
            startLaunch();
        } catch (Exception ex) {
            log.error("Error while handling before all event: {}", ex.getMessage(), ex);
        }

    }

    @Override
    public void afterAll(Results results) {

        try {
            stopLaunch(results);
        } catch (Exception ex) {
            log.error("Error while handling after all event: {}", ex.getMessage(), ex);
        }

    }

    @Override
    public boolean beforeStep(Step step, ScenarioContext context) {
        return true;
    }

    @Override
    public void afterStep(StepResult result, ScenarioContext context) {

    }

    @Override
    public String getPerfEventName(HttpRequestBuilder req, ScenarioContext context) {
        return null;
    }

    @Override
    public void reportPerfEvent(PerfEvent event) {

    }

}
