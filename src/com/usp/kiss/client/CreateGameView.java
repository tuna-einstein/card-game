package com.usp.kiss.client;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.service.CreateGameService;
import com.usp.kiss.client.service.CreateGameServiceAsync;
import com.usp.kiss.shared.AuthException;
import com.usp.kiss.shared.model.Game;

public class CreateGameView extends Composite {

    private static CreateGameViewUiBinder uiBinder = GWT
        .create(CreateGameViewUiBinder.class);

    interface CreateGameViewUiBinder extends UiBinder<Widget, CreateGameView> {
    }

    public @UiConstructor CreateGameView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    Image proceed;
    @UiField
    TextBox playerCount;
    
    @UiHandler("proceed")
    void onProceed(ClickEvent event) {
        createGame();
        DisplayStack.push(new Label("please wait..."));
    }
    
    
    private void createGame() {
        CreateGameServiceAsync gameService = GWT.create(CreateGameService.class);
        gameService.create(Integer.parseInt(playerCount.getText()), new AsyncCallback<Game>() {
            
            public void onSuccess(Game result) {
                DisplayStack.push(new GameTableView(result, false));
            }
            
            public void onFailure(Throwable caught) {
               if (caught instanceof AuthException) {
                   Window.open(((AuthException) caught).getLoginUrl(),  "_self", "");
               } else {
                   Window.alert("Error:" + caught);
               }
            }
        });
    }

}
