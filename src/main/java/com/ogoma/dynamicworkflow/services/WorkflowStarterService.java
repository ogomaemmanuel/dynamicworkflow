package com.ogoma.dynamicworkflow.services;

import com.ogoma.dynamicworkflow.abstractions.DynamicWorkflow;
import com.ogoma.dynamicworkflow.abstractions.WorkflowDefinition;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class WorkflowStarterService {
    private final WorkflowClient workflowClient;

    public WorkflowStarterService(
            WorkflowClient workflowClient
    ) {
        this.workflowClient = workflowClient;
    }

    public WorkflowExecution startWorkflow(
            WorkflowDefinition definition,
            Map<String, Object> context
    ) {

        String workflowId =
                definition.name() + "-" + UUID.randomUUID();

        DynamicWorkflow workflow =
                workflowClient.newWorkflowStub(
                        DynamicWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setWorkflowId(workflowId)
                                .setTaskQueue(
                                        "DYNAMIC_WORKFLOW_QUEUE"
                                )
                                .build()
                );


        return WorkflowClient.start(
                workflow::start,
                definition,
                context
        );
    }
}
