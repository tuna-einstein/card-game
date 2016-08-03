package com.usp.kiss.client.chart;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartSubtitle;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;

public class ExpectationChart {

    public static Chart createChart(Number[] values) {
        Chart chart = new Chart();
        chart.setType(Series.Type.LINE)
        .setMarginRight(130)  
        .setMarginBottom(25)  
        .setChartTitle(new ChartTitle()  
        .setText("Actual ~ Expectation")  
        .setX(-20))
        .setChartSubtitle(new ChartSubtitle()  
        .setText("Source: cardsnotnot.appspot.com")
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
        .setAxisTitleText("Difference count");  


        chart.addSeries(chart.createSeries()
                .setName("Actual ~ Expectation")  
                .setPoints(values));
        
        Number[] zeroLine = new Number[20];
        for (int i = 0; i < 20; i++) {
            zeroLine[i] = 0;
        }
        chart.addSeries(chart.createSeries()
                .setName("BaseLine")  
                .setPoints(zeroLine));
        return chart;
    }
}
