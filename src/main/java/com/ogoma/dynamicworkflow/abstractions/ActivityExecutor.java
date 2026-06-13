package com.ogoma.dynamicworkflow.abstractions;

import com.ogoma.dynamicworkflow.activities.WorkflowContext;

public interface ActivityExecutor <T extends ActivityDefinition> {

    Class<T> supports();


    void execute(
            T activity,
            WorkflowContext context
    );
}
