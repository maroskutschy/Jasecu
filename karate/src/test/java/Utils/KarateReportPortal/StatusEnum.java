package Utils.KarateReportPortal;

import java.util.Arrays;
import java.util.Optional;

public enum StatusEnum {

    /**
     * In progress status enum.
     */
    IN_PROGRESS("", false),
    /**
     * Passed status enum.
     */
    PASSED("passed", true),
    /**
     * Failed status enum.
     */
    FAILED("failed", false),
    /**
     * Stopped status enum.
     */
    STOPPED("stopped", false),
    /**
     * Skipped status enum.
     */
    SKIPPED("skipped", false),
    /**
     * Interrupted status enum.
     */
    INTERRUPTED("failed", false),
    /**
     * Cancelled status enum.
     */
    CANCELLED("cancelled", false);

    private final String executionCounterField;

    private final boolean positive;

    StatusEnum(String executionCounterField, boolean isPositive) {
        this.executionCounterField = executionCounterField;
        this.positive = isPositive;
    }

    /**
     * From value optional.
     *
     * @param value the value
     * @return the optional
     */
    public static Optional<StatusEnum> fromValue(String value) {
        return Arrays.stream(StatusEnum.values()).filter(status -> status.name().equalsIgnoreCase(value)).findAny();
    }

    /**
     * Is present boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isPresent(String name) {
        return fromValue(name).isPresent();
    }

    /**
     * Gets execution counter field.
     *
     * @return the execution counter field
     */
    public String getExecutionCounterField() {
        return executionCounterField;
    }

    /**
     * Is positive boolean.
     *
     * @return the boolean
     */
    public boolean isPositive() {
        return positive;
    }
}

