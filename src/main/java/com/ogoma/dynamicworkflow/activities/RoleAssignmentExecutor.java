package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RoleAssignmentExecutor implements ActivityExecutor<RoleAssignmentActivity> {
    private final SpelTemplateResolver resolver;

    public RoleAssignmentExecutor(SpelTemplateResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Class<RoleAssignmentActivity> supports() {
        return RoleAssignmentActivity.class;
    }

    @Override
    public void execute(RoleAssignmentActivity activity, WorkflowContext context) {
        var role = resolver.resolve(activity.getRoleID(), context);
        log.info("Role Assigment, Role ID {}", role);
    }
}
