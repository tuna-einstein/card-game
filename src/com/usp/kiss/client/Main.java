package com.usp.kiss.client;

import org.moxieapps.gwt.highcharts.client.Chart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.usp.kiss.client.chart.GameChart;

public class Main implements EntryPoint {

    Image im = new Image();

    private String email;
    private MainDashBoard mainView;
    public void onModuleLoad() {
        mainView = new MainDashBoard();
        DisplayStack.push(mainView);
        
    }
}