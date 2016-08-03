package com.usp.kiss.client;

public enum Suits {
    HEART("<font color=\"red\"> &hearts;</font>"),
    SPADE("<font color=\"black\"> &spades;</font>"),
    DIAMOND("<font color=\"red\"> &diams;</font>"),
    CLUB("<font color=\"black\"> &clubs;</font>"),
    NEUTRAL("<font color=\"red\"> N</font>");
    
    private final String value;
    Suits(String value)  {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
