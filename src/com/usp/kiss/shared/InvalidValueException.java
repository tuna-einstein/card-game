package com.usp.kiss.shared;

import java.io.Serializable;

public class InvalidValueException extends Exception implements Serializable {

    public InvalidValueException() {}

    public InvalidValueException(String cause) {
        super(cause);
    }
}
