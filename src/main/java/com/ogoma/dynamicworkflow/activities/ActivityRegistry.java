package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ActivityRegistry {

    private final Map<String, ActivityExecutor<? extends ActivityDefinition>> executors;

    public ActivityRegistry(
            List<ActivityExecutor<?>> activities
    ) {

        this.executors =
                activities.stream()
                        .collect(Collectors.toMap(
                                ActivityExecutor::type,
                                Function.identity()
                        ));
    }

    @SuppressWarnings("unchecked")
    public <T extends ActivityDefinition>
    ActivityExecutor<T> get(String type) {
        return (ActivityExecutor<T>)
                executors.get(type);
    }
}