package com.ogoma.dynamicworkflow.api.requests;

import com.ogoma.dynamicworkflow.abstractions.WorkflowDefinition;

import java.util.Map;

public record StartWorkflowRequest(
        WorkflowDefinition workflow,
        Map<String, Object> context
) {
}
