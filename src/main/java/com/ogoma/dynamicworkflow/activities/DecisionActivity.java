package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DecisionActivity extends ActivityDefinition {
    private String expression;
    private List<ActivityDefinition> onTrue;
    private List<ActivityDefinition> onFalse;
    private List<ActivityDefinition> onError = new ArrayList<>();
}
