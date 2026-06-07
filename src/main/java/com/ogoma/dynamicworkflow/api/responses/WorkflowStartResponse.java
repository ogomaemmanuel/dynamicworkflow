package com.ogoma.dynamicworkflow.api.responses;

public record WorkflowStartResponse(
        String workflowId,
        String runId,
        String status
) {
}
