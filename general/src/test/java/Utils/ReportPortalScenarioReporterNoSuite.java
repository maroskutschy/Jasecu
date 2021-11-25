package Utils;

import com.epam.reportportal.cucumber.ScenarioReporter;
import io.reactivex.Maybe;

import javax.annotation.Nonnull;
import java.util.Optional;

public class ReportPortalScenarioReporterNoSuite extends ScenarioReporter {

    @Override
    @Nonnull
    protected Optional<Maybe<String>> getRootItemId() {
        return Optional.empty();
    }

    @Override
    protected void startRootItem() {
    }

    @Override
    protected void finishRootItem() {
    }
}
