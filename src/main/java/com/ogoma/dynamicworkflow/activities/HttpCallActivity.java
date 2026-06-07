package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityDefinition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;


@Getter
@Setter
public class HttpCallActivity extends ActivityDefinition {
    private String url;
    private HttpMethod method;

    @Override
    public String getType() {
        return "";
    }
}
