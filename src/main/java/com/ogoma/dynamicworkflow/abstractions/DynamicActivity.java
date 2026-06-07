package com.ogoma.dynamicworkflow.abstractions;

import io.temporal.activity.ActivityInterface;

import java.util.Map;

@ActivityInterface
public interface DynamicActivity {

    void execute(
            ActivityDefinition activity,
            Map<String, Object> context
    );
}
