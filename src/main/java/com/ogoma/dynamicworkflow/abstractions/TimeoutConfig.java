package com.ogoma.dynamicworkflow.abstractions;

import java.time.Duration;

public record TimeoutConfig(
        Duration startToClose,
        Duration scheduleToClose,
        Duration heartbeat
) {

    public static TimeoutConfig defaults() {
        return new TimeoutConfig(
                Duration.ofMinutes(5),
                null,
                null
        );
    }
}
