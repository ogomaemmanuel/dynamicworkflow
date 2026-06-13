package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendEmailExecutor implements ActivityExecutor<SendEmailActivity> {
    private final SpelTemplateResolver resolver;

    public SendEmailExecutor(SpelTemplateResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Class<SendEmailActivity> supports() {
        return SendEmailActivity.class;
    }

    @Override
    public void execute(
            SendEmailActivity activity,
            WorkflowContext context) {
        String subject = resolver.resolve(activity.getSubject(), context);
        String recipient = resolver.resolve(activity.getRecipient(), context);
        String body = resolver.resolve(activity.getBody(), context);
        log.info("Email Subject {}", subject);
        log.info("Email body {}", body);
        log.info("Email recipient {}", recipient);
    }
}
