package com.usp.kiss.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class BusyWidget {

    public static Widget get() {
        Image image = new Image("wave.gif");
        image.setWidth("100%");
        image.setHeight("100%");
        return image;
    }
    
    public static Widget getLoading() {
        Image image = new Image("loading.gif");
        //image.setWidth("100%");
        image.setHeight("100%");
        return image;
    }
}
