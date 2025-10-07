package com.developerstring.jetco_kmp.components.stepper.model

/**
 * Enum representing the possible statuses of a step within the stepper.
 * @property COMPLETE Step has been completed successfully.
 * @property ACTIVE Step is currently active or in progress.
 * @property IDLE Step is inactive or pending.
 * @property ERROR Step has encountered an error state.
 */
enum class StepperStatus {
    /** Step is completed successfully. */
    COMPLETE,

    /** Step is currently active / in progress. */
    ACTIVE,

    /** Step is inactive or pending. */
    IDLE,

    /** Step has encountered an error state. */
    ERROR
}