package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import lombok.Getter;
import lombok.Setter;


import java.util.Map;


@Getter
@Setter
public class HttpCallActivity extends ActivityDefinition {
    private String url;
    private HttpMethod method;
    private Map<String, String> headers;
    private Object body;
    public enum HttpMethod{
        POST,GET,PUT,PATCH,DELETE
    }
}
