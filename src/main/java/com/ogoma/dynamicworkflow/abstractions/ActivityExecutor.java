package com.ogoma.dynamicworkflow.abstractions;

import com.ogoma.dynamicworkflow.activities.WorkflowContext;

public interface ActivityExecutor <T extends ActivityDefinition> {
    String type();

    void execute(
            T activity,
            WorkflowContext context
    );
}
