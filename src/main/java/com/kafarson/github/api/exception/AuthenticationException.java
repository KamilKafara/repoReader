package com.kafarson.github.api.exception;

import java.util.Collection;

public class AuthenticationException extends InternalException {

    public AuthenticationException(String message, Collection<FieldInfo> fields) {
        super(message, fields);
    }
}
