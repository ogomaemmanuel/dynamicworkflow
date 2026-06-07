package com.ogoma.dynamicworkflow.activities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalTaskActivity extends ActivityDefinition{
    private String role;

    private Integer dueDays;
}
