package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import com.ogoma.dynamicworkflow.abstractions.DynamicActivity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamicActivityImpl implements DynamicActivity {
    private final ActivityRegistry registry;
    public DynamicActivityImpl(
            ActivityRegistry registry
    ) {
        this.registry = registry;
    }
    @Override
    public void execute(
            ActivityDefinition activity,
            Map<String, Object> contextMap
    ) {
        WorkflowContext context =
                new WorkflowContext();
        contextMap.forEach(context::put);
        ActivityExecutor<ActivityDefinition> executor =
                registry.get(activity.getClass());
        executor.execute(activity, context);
    }
}
