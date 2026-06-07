package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailActivity extends ActivityDefinition {
    private String template;

    private String recipient;

    @Override
    public String getType() {
        return "";
    }
}
