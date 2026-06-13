package com.ogoma.dynamicworkflow.abstractions;

import java.time.Duration;
import java.util.List;

public record RetryConfig(
        int maxAttempts,
        Duration initialInterval,
        double backoffCoefficient,
        Duration maximumInterval,
        List<String> nonRetryableErrors
) {

    public static RetryConfig defaults() {
        return new RetryConfig(
                3,
                Duration.ofSeconds(1),
                2.0,
                Duration.ofMinutes(1),
                List.of()
        );
    }
}
