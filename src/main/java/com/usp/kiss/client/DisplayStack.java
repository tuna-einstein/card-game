package com.usp.kiss.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DisplayStack {

    private static final List<Widget> widgets = new ArrayList<Widget>();
    
    public static void push(Widget widget) {
        if (widgets.size() > 0) {
            RootLayoutPanel.get().remove(0);
        }
        RootLayoutPanel.get().add(widget);
        widgets.add(widget);
        RootLayoutPanel.get().forceLayout();
    }

    public static void pop() {
        if (widgets.size() > 0) {
            RootLayoutPanel.get().remove(0);
            widgets.remove(widgets.size() - 1);
        }
        if (widgets.size() > 0) {
            RootLayoutPanel.get().add(widgets.get(widgets.size() - 1));
            RootLayoutPanel.get().forceLayout();
        } else {
            Window.Location.reload();
        }
    }
}
