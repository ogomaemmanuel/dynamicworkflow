package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalTaskActivity extends ActivityDefinition {
    private String role;

    private Integer dueDays;

}
