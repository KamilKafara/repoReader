package com.kafarson.github.api.exception;

import lombok.Getter;

import java.util.Collection;

@Getter
public class InternalException extends RuntimeException {
    private final Collection<FieldInfo> fields;

    public InternalException(String message, Collection<FieldInfo> fields) {
        super(message);
        this.fields = fields;
    }
}
