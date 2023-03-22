package com.kafarson.github.api.exception;

import java.util.Collection;

public class BadRequestException extends InternalException {
    public BadRequestException(String message, Collection<FieldInfo> fields) {
        super(message, fields);
    }
}
