package com.ogoma.dynamicworkflow.activities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ogoma.dynamicworkflow.activities.SendEmailActivity;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
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
        )
})
@Getter
@Setter
public abstract class ActivityDefinition {
        private String id;

        private String name;
        /**
         * Optional SpEL expression
         */
        private String condition;
}
