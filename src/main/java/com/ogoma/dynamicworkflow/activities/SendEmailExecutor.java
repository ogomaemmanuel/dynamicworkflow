package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import org.springframework.stereotype.Component;

@Component
public class SendEmailExecutor implements ActivityExecutor<SendEmailActivity> {
    @Override
    public String type() {
        return "SEND_EMAIL";
    }

    @Override
    public void execute(
            SendEmailActivity activity,
            WorkflowContext context) {

    }
}
