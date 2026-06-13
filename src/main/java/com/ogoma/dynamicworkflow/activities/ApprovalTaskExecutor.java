package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import org.springframework.stereotype.Component;

@Component
public class ApprovalTaskExecutor implements ActivityExecutor<ApprovalTaskActivity> {

    @Override
    public Class<ApprovalTaskActivity> supports() {
        return ApprovalTaskActivity.class;
    }

    @Override
    public void execute(
            ApprovalTaskActivity activity,
            WorkflowContext context
    ) {

        System.out.println(
                "Creating approval task"
        );
    }
}
