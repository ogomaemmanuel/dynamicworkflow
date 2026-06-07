package com.ogoma.dynamicworkflow.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowStarterService workflowStarterService;

    public WorkflowController(
            WorkflowStarterService workflowStarterService
    ) {
        this.workflowStarterService =
                workflowStarterService;
    }

    @PostMapping
    public ResponseEntity<WorkflowStartResponse> startWorkflow(
            @RequestBody StartWorkflowRequest request
    ) {

        WorkflowExecution execution =
                workflowStarterService.startWorkflow(
                        request.workflow(),
                        request.context()
                );

        return ResponseEntity.accepted()
                .body(
                        new WorkflowStartResponse(
                                execution.getWorkflowId(),
                                execution.getRunId(),
                                "STARTED"
                        )
                );
    }
}