package com.usp.kiss.client.chart;

import java.util.ArrayList;
import java.util.List;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartSubtitle;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;

public class ScoreChart {

    public static class PlayerScore {
        private String playerName;
        private Number[] scores;

        public String getPlayerName() {
            return playerName;
        }
        public PlayerScore setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }
        public Number[] getScores() {
            return scores;
        }
        public PlayerScore setScores(Number[] scores) {
            this.scores = scores;
            return this;
        }
    }

    private List<PlayerScore> players = new ArrayList<PlayerScore>();

    public ScoreChart addPlayer(PlayerScore player) {
        players.add(player);
        return this;
    }

    public void removePlayer(PlayerScore player) {
        players.remove(player);
    }

    public Chart createChart() {
        Chart chart = new Chart();
        chart.setType(Series.Type.LINE)
        .setMarginRight(130)  
        .setMarginBottom(25)  
        .setChartTitle(new ChartTitle()  
        .setText("Points Graph")  
        .setX(-20))
        .setChartSubtitle(new ChartSubtitle()  
        .setText("Source: ")
        .setX(-20))
        .setLegend(new Legend()  
        .setLayout(Legend.Layout.VERTICAL)  
        .setAlign(Legend.Align.RIGHT)  
        .setVerticalAlign(Legend.VerticalAlign.TOP)  
        .setX(-10)  
        .setY(100)  
        .setBorderWidth(0))  
        .setToolTip(new ToolTip()  
        .setFormatter(new ToolTipFormatter() {  
            public String format(ToolTipData toolTipData) {  
                return "<b>" + toolTipData.getSeriesName() + "</b><br/>" +  
                        toolTipData.getXAsString() + ": " + toolTipData.getYAsDouble();  
            }  
        }));  

        chart.getXAxis()
        .setCategories(
                "10H", "9S", "8D", "7C", "6N", "5H",  
                "4S", "3D", "2S", "1N", "1H", "2S", "3D",
                "4C", "5N", "6H", "7S", "8D", "9C", "10N");  

        chart.getYAxis()  
        .setAxisTitleText("Points");  

        for (PlayerScore score : players) {
            chart.addSeries(chart.createSeries()  
                    .setName(score.getPlayerName())  
                    .setPoints(score.getScores()));  
        }
        return chart;
    }
}
