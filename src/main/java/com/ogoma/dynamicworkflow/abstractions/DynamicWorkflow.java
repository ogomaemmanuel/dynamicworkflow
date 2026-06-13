package com.ogoma.dynamicworkflow.abstractions;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.Map;

@WorkflowInterface
public interface DynamicWorkflow {

    @WorkflowMethod
    void start(
            WorkflowDefinition definition,
            Map<String, Object> context
    );

    @SignalMethod
    void signal(
            String signalName,
            Object payload
    );
}
