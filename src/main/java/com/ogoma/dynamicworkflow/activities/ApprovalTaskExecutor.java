package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
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
        log.info(" Creating approval task");
    }
}
