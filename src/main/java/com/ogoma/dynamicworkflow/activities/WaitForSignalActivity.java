package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import lombok.Getter;

import java.time.Duration;

@Getter
public class WaitForSignalActivity extends ActivityDefinition {

    /**
     * Signal name.
     */
    private String signalName;

    /**
     * Optional timeout.
     */
    private Duration waitTimeout;

    /**
     * Optional timeout expression.
     */
    private String timeoutExpression;

    /**
     * Variable name where signal payload is stored.
     */
    private String outputVariable;

    /**
     * Optional condition to validate payload.
     */
    private String acceptanceCondition;

    // getters/setters
}
