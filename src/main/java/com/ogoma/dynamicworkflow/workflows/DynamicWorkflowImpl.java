package com.ogoma.dynamicworkflow.workflows;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import com.ogoma.dynamicworkflow.abstractions.DynamicActivity;
import com.ogoma.dynamicworkflow.abstractions.DynamicWorkflow;
import com.ogoma.dynamicworkflow.abstractions.WorkflowDefinition;
import com.ogoma.dynamicworkflow.activities.ConditionEvaluator;
import com.ogoma.dynamicworkflow.activities.DurationResolver;
import com.ogoma.dynamicworkflow.activities.TimerActivity;
import com.ogoma.dynamicworkflow.activities.WorkflowContext;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DynamicWorkflowImpl implements DynamicWorkflow {

    private final Map<String, Object> receivedSignals =
            new HashMap<>();
    private final DynamicActivity dynamicActivity =
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
            Map<String, Object> contextMap
    ) {

        WorkflowContext context =
                new WorkflowContext();

        contextMap.forEach(context::put);

        ConditionEvaluator evaluator =
                new ConditionEvaluator();
        for (ActivityDefinition activity :
                workflow.activities()) {
            if (!evaluator.evaluate(
                    activity.getCondition(),
                    context
            )) {
                continue;
            }
            if (activity instanceof TimerActivity timer) {
                DurationResolver durationResolver =
                        new DurationResolver();

                Duration duration =
                        durationResolver.resolve(
                                timer,
                                context
                        );
                Workflow.sleep(duration);
                continue;
            }

            dynamicActivity.execute(
                    activity,
                    context.variables()
            );
        }
    }

    @Override
    public void signal(
            String signalName,
            Object payload
    ) {
        receivedSignals.put(
                signalName,
                payload
        );
    }
}
