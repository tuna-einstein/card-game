package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class DockTestView extends Composite {

    private static DockTestViewUiBinder uiBinder = GWT
        .create(DockTestViewUiBinder.class);

    interface DockTestViewUiBinder extends UiBinder<Widget, DockTestView> {
    }

    public DockTestView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

   

}
