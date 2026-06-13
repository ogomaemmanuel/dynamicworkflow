package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import org.springframework.stereotype.Component;

@Component
public class DecisionActivityExecutor implements ActivityExecutor<DecisionActivity> {

    private final SpelTemplateResolver resolver;

    public DecisionActivityExecutor(
            SpelTemplateResolver resolver
    ) {
        this.resolver = resolver;
    }

    @Override
    public Class<DecisionActivity> supports() {
        return DecisionActivity.class;
    }

    @Override
    public void execute(
            DecisionActivity activity,
            WorkflowContext context
    ) {

        String evaluated =
                resolver.resolve(
                        activity.getExpression(),
                        context
                );

        boolean result =
                Boolean.parseBoolean(evaluated);

        String route =
                result ? "TRUE" : "FALSE";

        context.put(
                activity.getId() + ".result",
                result
        );

        context.put(
                activity.getId() + ".route",
                route
        );

        context.put(
                activity.getId() + ".nextActivities",
                result
                        ? activity.getOnTrue()
                        : activity.getOnFalse()
        );
    }
}
