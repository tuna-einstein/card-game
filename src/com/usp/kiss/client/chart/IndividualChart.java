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
import org.moxieapps.gwt.highcharts.client.plotOptions.SplinePlotOptions;

public class IndividualChart {

    public static class Data {
        private String name;
        private Number[] expected;
        private Number[] actual;
        private Number[] score;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Number[] getExpected() {
            return expected;
        }
        public void setExpected(Number[] expected) {
            this.expected = expected;
        }
        public Number[] getActual() {
            return actual;
        }
        public void setActual(Number[] actual) {
            this.actual = actual;
        }
        public Number[] getScore() {
            return score;
        }
        public void setScore(Number[] score) {
            this.score = score;
        }
    }
    
    public Chart createChart(Data data) {  
        
        final Chart chart = new Chart()  
            .setChartTitleText(data.getName() + "'s score analysis")  
            .setChartSubtitleText("Source: cardsnotnot.appspot.com")  
            .setZoomType(Chart.ZoomType.X_AND_Y)  
            .setToolTip(new ToolTip()  
                .setFormatter(new ToolTipFormatter() {  
                    public String format(ToolTipData toolTipData) {  
                        return toolTipData.getXAsString() + ": " + toolTipData.getYAsDouble();  
                    }  
                })  
            )  
            .setLegend(new Legend()  
                .setLayout(Legend.Layout.VERTICAL)  
                .setAlign(Legend.Align.LEFT)  
                .setVerticalAlign(Legend.VerticalAlign.TOP)  
                .setX(120)  
                .setY(100)  
                .setFloating(true)  
                .setBackgroundColor("#FFFFFF")  
            );  
  
        chart.getXAxis()
        .setCategories(
                "10H", "9S", "8D", "7C", "6N", "5H",  
                "4S", "3D", "2S", "1N", "1H", "2S", "3D",
                "4C", "5N", "6H", "7S", "8D", "9C", "10N"); 
  
        // Primary yAxis  
        chart.getYAxis(0)  
            .setAxisTitle(new AxisTitle()  
                .setText("Count")  
            )  
            .setLabels(new YAxisLabels()  
                .setStyle(new Style()  
                    .setColor("#89A54E")  
                )  
                .setFormatter(new AxisLabelsFormatter() {  
                    public String format(AxisLabelsData axisLabelsData) {  
                        return axisLabelsData.getValueAsLong() + "";  
                    }  
                })  
            );  
  
        // Secondary yAxis  
        chart.getYAxis(1)  
            .setAxisTitle(new AxisTitle()  
                .setText("Score")  
            )  
            .setOpposite(true)  
            .setLabels(new YAxisLabels()  
                .setStyle(new Style()  
                    .setColor("#4572A7")  
                )  
                .setFormatter(new AxisLabelsFormatter() {  
                    public String format(AxisLabelsData axisLabelsData) {  
                        return axisLabelsData.getValueAsLong() + "";  
                    }  
                })  
            );  
  
        chart.addSeries(chart.createSeries()  
            .setName("Expected")  
            .setType(Series.Type.COLUMN)  
            
            .setYAxis(0)  
            .setPoints(data.getExpected())  
        );  
        
        chart.addSeries(chart.createSeries()  
                .setName("Actual")  
                .setType(Series.Type.COLUMN)  
                
                .setYAxis(0)  
                .setPoints(data.getActual())  
            ); 
        
        chart.addSeries(chart.createSeries()  
            .setName("Score")  
            .setType(Series.Type.SPLINE)
            .setYAxis(1)  
            .setPlotOptions(new SplinePlotOptions()  
                .setColor("#89A54E")  
            )  
            .setPoints(data.getScore())  
        );  
  
        return chart;  
    }  
}
