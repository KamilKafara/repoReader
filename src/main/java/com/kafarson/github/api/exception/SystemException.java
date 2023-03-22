package com.kafarson.github.api.exception;

import lombok.Getter;

import java.util.Collection;

@Getter
public class SystemException extends InternalException {
    private final String details;

    public SystemException(String message, String details, Collection<FieldInfo> fields) {
        super(message, fields);
        this.details = details;
    }
}
