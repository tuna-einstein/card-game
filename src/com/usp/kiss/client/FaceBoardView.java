package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FaceBoardView extends Composite {

    private static FaceBoardViewUiBinder uiBinder = GWT
            .create(FaceBoardViewUiBinder.class);

    interface FaceBoardViewUiBinder extends UiBinder<Widget, FaceBoardView> {
    }
    
    @UiField
    VerticalPanel container;

    public FaceBoardView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

  

//    @UiHandler("backButton")
//    void onClick(ClickEvent e) {
//        RootPanel.get().clear();
//        RootPanel.get().add(RootLayoutPanel.get());
//    }
//    
    public void addContent(Widget widget) {
        container.add(widget);
    }
}
