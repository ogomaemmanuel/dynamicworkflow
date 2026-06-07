package com.ogoma.dynamicworkflow.abstractions;

import java.util.List;

public record WorkflowDefinition (
        String name,
        List<ActivityDefinition> activities
){
}
