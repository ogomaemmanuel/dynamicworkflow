package com.ogoma.dynamicworkflow.activities;

public record DecisionResult(
        boolean outcome,
        String evaluatedExpression
) {}
