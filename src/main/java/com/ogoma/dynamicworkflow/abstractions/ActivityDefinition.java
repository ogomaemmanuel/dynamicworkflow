package com.ogoma.dynamicworkflow.abstractions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ogoma.dynamicworkflow.activities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = SendEmailActivity.class,
                name = "SEND_EMAIL"
        ),
        @JsonSubTypes.Type(
                value = HttpCallActivity.class,
                name = "HTTP_CALL"
        ),
        @JsonSubTypes.Type(
                value = ApprovalTaskActivity.class,
                name = "APPROVAL_TASK"
        ),
        @JsonSubTypes.Type(
                value = RoleAssignmentActivity.class,
                name = "ROLE_ASSIGNMENT"
        ),
        @JsonSubTypes.Type(
                value = DecisionActivity.class,
                name = "DECISION"
        ),

        @JsonSubTypes.Type(
                value = TimerActivity.class,
                name = "TIMER"
        ),

})
@Getter
@Setter
public abstract class ActivityDefinition {
    private UUID id;

    private String name;

    private String type;

    String resultKey;
    /**
     * Optional SpEL expression
     */
    private String condition;

    private ActivityDefinition compensation;
    private boolean compensatable = true;
    private RetryConfig retry = RetryConfig.defaults();

    private TimeoutConfig timeout =
            TimeoutConfig.defaults();

    protected ActivityDefinition() {
        this.id = UUID.randomUUID();
    }
}
