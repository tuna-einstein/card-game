package com.usp.kiss.client;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.service.CreateGameService;
import com.usp.kiss.client.service.CreateGameServiceAsync;
import com.usp.kiss.shared.AuthException;
import com.usp.kiss.shared.model.UserInfo;

public class DashBoardView extends Composite {

    private static DashBoardViewUiBinder uiBinder = GWT
        .create(DashBoardViewUiBinder.class);

    interface DashBoardViewUiBinder extends UiBinder<Widget, DashBoardView> {
    }

    @UiField
    VerticalPanel container;
    
    @UiField
    Image imagePlus;
    
    public @UiConstructor DashBoardView(List<UserInfo> users) {
        initWidget(uiBinder.createAndBindUi(this));
        for (UserInfo user : users) {
       //     SingleUserView userView = new SingleUserView(user);
          //  container.add(userView);
        }
        imagePlus.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               // createGame();
            }
        });
    }
    
    
}
