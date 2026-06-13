package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;

import java.time.Duration;

public class TimerActivity extends ActivityDefinition {

    /**
     * Fixed duration
     */
    private Duration duration;

    /**
     * Optional SpEL expression.
     *
     * Examples:
     * "#reviewDueDays"
     * "#waitHours"
     */
    private String durationExpression;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getDurationExpression() {
        return durationExpression;
    }

    public void setDurationExpression(String durationExpression) {
        this.durationExpression = durationExpression;
    }
}
