package com.kafarson.github.api.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ResponseErrorInfo {
    private String message;
    private String details;
    private Collection<FieldInfo> fields;
}
