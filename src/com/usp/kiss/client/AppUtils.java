package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.usp.kiss.shared.model.Episode;

public class AppUtils {

    public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);
    
    private static String userEmail;
    
    public static int getScore(int expected, int actual) {
        if (expected < 0 || actual < 0) {
            return 0;
        }
        if (expected == actual) {
            if (expected == 0) {
                return 10;
            } else {
                return expected*11;
            }
        } else {
            return Math.abs(expected - actual) * -11;
        }
    }
    
    public static int[] computeScore(Episode episode) {
        int[] expected = episode.getExpected();
        int actual[] = episode.getActual();
        
        int[] score = new int[actual.length];
        for (int i = 0; i < actual.length; i++) {
            score[i] = getScore(expected[i], actual[i]);
        }
        return score;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        AppUtils.userEmail = userEmail;
    }
}



