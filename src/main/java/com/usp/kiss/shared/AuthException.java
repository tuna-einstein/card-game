package com.usp.kiss.shared;

import java.io.Serializable;

public class AuthException extends Exception implements Serializable {

    private String loginUrl;

    public AuthException() {}

    public AuthException(String loginUrl) {
        super("Authentication failed...");
        this.loginUrl = loginUrl;
    }

    public String getLoginUrl() {
        return this.loginUrl;
    }
}
