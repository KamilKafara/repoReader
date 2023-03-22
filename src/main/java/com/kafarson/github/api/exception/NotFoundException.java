package com.kafarson.github.api.exception;

import java.util.Collection;

public class NotFoundException extends InternalException {

    public NotFoundException(String message, Collection<FieldInfo> fields) {
        super(message, fields);
    }
}
