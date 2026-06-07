package com.ogoma.dynamicworkflow.activities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailActivity extends ActivityDefinition{
    private String template;

    private String recipient;
}
