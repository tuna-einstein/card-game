package com.usp.kiss.client.custom;

import com.google.gwt.user.client.ui.Label;

public class ColorLabel extends Label {

    public ColorLabel() {
        this("");
    }

    public ColorLabel(String valueOf) {
        super();
        setText(valueOf);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setStyleName("myLabelCss");

        getElement().getStyle().setColor("white");
        if (text.startsWith("-")) {
            getElement().getStyle().setBackgroundColor("red");
        } else {
            getElement().getStyle().setBackgroundColor("green");
        }
        if (text.equals("0") || text.isEmpty()) {
            getElement().getStyle().setBackgroundColor("white");
            getElement().getStyle().setColor("black");
        }
    }
}
