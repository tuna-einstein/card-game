package com.usp.kiss.client.chart;

import org.moxieapps.gwt.highcharts.client.AxisTitle;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.YAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.ColumnPlotOptions;

public class HitChart {

    public static class Hit {
        private String playerName = "";
        private Number hitCount = 0;
        private Number score = 0;
        public String getPlayerName() {
            return playerName;
        }
        public Hit setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }
        public Number getHitCount() {
            return hitCount;
        }
        public Hit setHitCount(Number hitCount) {
            this.hitCount = hitCount;
            return this;
        }

        public void incrementHitCountByOne() {
            hitCount = hitCount.intValue() + 1;
        }

        public void incrementScore(int v) {
            score = score.intValue() + v;
        }

        public Number getScore() {
            return score;
        }
        public void setScore(Number score) {
            this.score = score;
        }
    }

    public Chart createChart(Hit[] hits) {  

        final Chart chart = new Chart()  
        .setType(Series.Type.COLUMN)  
        .setChartTitleText("Score & Hits")  
        .setChartSubtitleText("Source: cardsnotnot.appspot.com")  
        .setColumnPlotOptions(new ColumnPlotOptions()  
        .setPointPadding(0.2)  
        .setBorderWidth(0)  
                )  
                .setLegend(new Legend()  
                .setLayout(Legend.Layout.VERTICAL)  
                .setAlign(Legend.Align.LEFT)  
                .setVerticalAlign(Legend.VerticalAlign.TOP)  
                .setX(100)  
                .setY(70)  
                .setFloating(true)

                .setBackgroundColor("#FFFFFF")  
                .setShadow(true)  
                        )  
                        .setToolTip(new ToolTip()  
                        .setFormatter(new ToolTipFormatter() {  
                            public String format(ToolTipData toolTipData) {  
                                return toolTipData.getXAsString() + ": " + toolTipData.getYAsLong();  
                            }  
                        })  
                                );  



        // Primary yAxis  
        chart.getYAxis(1)  
        .setLabels(new YAxisLabels()  
        .setStyle(new Style()  
        .setColor("#AA4643")  
                )  
                .setFormatter(new AxisLabelsFormatter() {  
                    public String format(AxisLabelsData axisLabelsData) {  
                        return axisLabelsData.getValueAsLong() + "";  
                    }  
                })  
                )  
                .setAxisTitle(new AxisTitle()  
                .setText("Hits")  
                .setStyle(new Style()  
                .setColor("#AA4643")  
                        )  
                        )  
                        .setOpposite(true);  

        chart.getYAxis(0)  
        .setLabels(new YAxisLabels()  
        .setStyle(new Style()  
        .setColor("#4572A7")  
                )  
                .setFormatter(new AxisLabelsFormatter() {  
                    public String format(AxisLabelsData axisLabelsData) {  
                        return axisLabelsData.getValueAsLong() + "";  
                    }  
                })  
                )  
                .setAxisTitle(new AxisTitle()  
                .setText("Score")  
                .setStyle(new Style()  
                .setColor("#4572A7")  
                        )  
                        )  
                        .setGridLineWidth(1);  

        String[] names = new String[hits.length];
        int index = 0;
        Number[] hitNumber = new Number[hits.length];
        Number[] score = new Number[hits.length];

        for (Hit hit : hits) {
            names[index] = hit.getPlayerName();
            hitNumber[index] = hit.getHitCount();
            score[index] = hit.getScore();
            index ++;
        }

        chart.getXAxis()  
        .setCategories(names);  

        chart.getYAxis(1)  
        .setAxisTitleText("Hit count")  
        .setMin(0);

        chart.addSeries(chart.createSeries()  
                .setName("Score")  
                .setType(Series.Type.COLUMN)  
                .setPlotOptions(new ColumnPlotOptions()  
                .setColor("#4572A7")  
                        )  
                        .setYAxis(0)  
                        .setPoints(score)  
                );  

        chart.addSeries(chart.createSeries()  
                .setName("Hits")  
                .setType(Series.Type.COLUMN)  
                .setPlotOptions(new ColumnPlotOptions()  
                .setColor("#AA4643")  
                        )  
                        .setYAxis(1)  
                        .setPoints(hitNumber)  
                ); 
        return chart;  
    }  

}
