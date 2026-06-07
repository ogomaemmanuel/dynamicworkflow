package com.ogoma.dynamicworkflow.activities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorkflowContext {

    private final Map<String, Object> variables =
            new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        variables.put(key, value);
    }

    public Object get(String key) {
        return variables.get(key);
    }

    public Map<String, Object> variables() {
        return variables;
    }
}
