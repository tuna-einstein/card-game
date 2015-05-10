package com.usp.kiss.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.service.ListGamesService;
import com.usp.kiss.client.service.ListGamesServiceAsync;
import com.usp.kiss.shared.model.Game;

public class SingleUserView extends Composite {

    private static SingleUserViewUiBinder uiBinder = GWT
        .create(SingleUserViewUiBinder.class);

    interface SingleUserViewUiBinder extends UiBinder<Widget, SingleUserView> {
    }

    @UiField VerticalPanel container;
    public @UiConstructor SingleUserView() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public void listGames(final String userEmail) {
        container.clear();
       
        container.add(new Label("Please wait...."));
        ListGamesServiceAsync gamesService = GWT.create(ListGamesService.class);
        gamesService.list(userEmail, new AsyncCallback<List<Game>>() {
            
            public void onSuccess(List<Game> result) {
                updateUi(result);
            }
            
            public void onFailure(Throwable caught) {
                container.clear();
                Window.alert("Error: " + caught);
            }
        });
    }
    
    private void updateUi(List<Game> games) {
        container.clear();
        for (Game game : games) {
            container.add(new SingleGameView(game));   
        }
    }
}
