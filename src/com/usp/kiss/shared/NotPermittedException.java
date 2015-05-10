package com.usp.kiss.shared;

import java.io.Serializable;

public class NotPermittedException extends Exception implements Serializable {

    private String reason;

    public NotPermittedException() {}

    public NotPermittedException(String reason) {
        super(reason);
        this.reason = reason;
    }

    public String getLoginUrl() {
        return this.reason;
    }
}

