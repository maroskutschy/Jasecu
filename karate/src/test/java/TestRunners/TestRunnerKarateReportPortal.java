package TestRunners;

import Utils.KarateReportPortal.LogHelper;
import Utils.KarateReportPortal.ReportPortalHook;
import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.RunnerOptions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Collections;

/**
 * This class is to execute tests Karate Tests and send results to report Portal
 */

@KarateOptions(features = "src/test/java/TestRunners/featuresKarate")
class TestRunnerKarateReportPortal {

    /* Initialize the logger */
    private static final Logger log = LogHelper.getLogger();

    /**
     * This method is to launch tests in parallel as per thread counts
     *
     * @throws IOException the io exception
     */
    @Test
        void testParallel() throws IOException {
        log.info("+------------------------------------+");
        log.info("| Starting Functional Test Execution |");
        log.info("+------------------------------------+");

        RunnerOptions options = RunnerOptions.fromAnnotationAndSystemProperties(null, null, getClass());
        Results results = Runner.parallel(options.getTags(),
                options.getFeatures(),
                options.getName(),
                Collections.singletonList(new ReportPortalHook()),
                1,
                null);

        int countFeatures = results.getFeatureCount();
        int countScenarios = results.getScenarioCount();
        if (countFeatures == 0 && countScenarios == 0) {
            log.error("+-----------------------------------------------+");
            log.error("| DID NOT FIND ANY FEATURES OR SCENARIOS TO RUN |");
            log.error("|       FEATURES = {}       SCENARIOS = {}      |", countFeatures, countScenarios);
            log.error("+-----------------------------------------------+");
        } else {
            log.info("Execution completed for FEATURES = {}   and   SCENARIOS = {}", countFeatures, countScenarios);
        }
    }

}

