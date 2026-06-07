package com.ogoma.dynamicworkflow.workflows;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import com.ogoma.dynamicworkflow.abstractions.DynamicActivity;
import com.ogoma.dynamicworkflow.abstractions.DynamicWorkflow;
import com.ogoma.dynamicworkflow.abstractions.WorkflowDefinition;
import com.ogoma.dynamicworkflow.activities.ConditionEvaluator;
import com.ogoma.dynamicworkflow.activities.WorkflowContext;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.Map;

public class DynamicWorkflowImpl
        implements DynamicWorkflow {

    private final DynamicActivity activity =
            Workflow.newActivityStub(
                    DynamicActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(
                                    Duration.ofMinutes(5)
                            )
                            .build()
            );

    @Override
    public void start(
            WorkflowDefinition workflow,
            Map<String, Object> context
    ) {

        ConditionEvaluator evaluator =
                new ConditionEvaluator();

        WorkflowContext workflowContext =
                new WorkflowContext();

        context.forEach(workflowContext::put);

        for (ActivityDefinition definition :
                workflow.activities()) {

            if (!evaluator.evaluate(
                    definition.getCondition(),
                    workflowContext
            )) {
                continue;
            }

            activity.execute(
                    definition,
                    workflowContext.variables()
            );
        }
    }
}
