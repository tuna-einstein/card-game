package com.usp.kiss.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.usp.kiss.client.event.AggScoreChangedEvent;
import com.usp.kiss.shared.model.Episode;

public class AppUtils {

    public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

    private static String userEmail = "";

    private static List<String> playerNames;

    private static int[] aggScores;

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

    public static List<String> getPlayerNames() {
        return playerNames;
    }

    public static void setPlayerNames(List<String> playerNames) {
        AppUtils.playerNames = playerNames;
        for (int i = 0; i < playerNames.size(); i++) {
            playerNames.set(i, capitalize(playerNames.get(i)));
        }
    }

    public static String getDealer(int widgetId) {
        return playerNames.get(widgetId % playerNames.size());
    }

    public static int getCardCount(String episodeTitle) {
        return Integer.parseInt(episodeTitle.substring(0, episodeTitle.length() - 1));
    }

    public static Suits getSuit(String episodeTitle) {
        String v = episodeTitle.substring(episodeTitle.length() - 1);
        if (v.equals("H")) {
            return Suits.HEART;
        }
        if (v.equals("S")) {
            return Suits.SPADE;
        }
        if (v.equals("D")) {
            return Suits.DIAMOND;
        }
        if (v.equals("C")) {
            return Suits.CLUB;
        }
        return Suits.NEUTRAL;
    }


    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static int[] getAggScores() {
        return aggScores;
    }

    public static void setAggScores(int[] aggScores) {
        AppUtils.aggScores = aggScores;
        // AppUtils.EVENT_BUS.fireEvent(new AggScoreChangedEvent());
    }

    public static int getMaxScore() {
        if (aggScores == null) {
            return -1;
        }
        int max = aggScores[0];
        for (int v : aggScores) {
            if (max < v) {
                max = v;
            }
        }
        return max;
    }
}



