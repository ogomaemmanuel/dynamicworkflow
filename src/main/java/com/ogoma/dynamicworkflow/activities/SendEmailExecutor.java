package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;

public class SendEmailExecutor implements ActivityExecutor<SendEmailActivity> {
    @Override
    public String type() {
        return "";
    }

    @Override
    public void execute(
            SendEmailActivity activity,
            WorkflowContext context) {

    }
}
