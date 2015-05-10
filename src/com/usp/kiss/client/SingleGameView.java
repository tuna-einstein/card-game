package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.shared.model.Game;

public class SingleGameView extends Composite {

    private static SingleGameViewUiBinder uiBinder = GWT
            .create(SingleGameViewUiBinder.class);

    interface SingleGameViewUiBinder extends UiBinder<Widget, SingleGameView> {
    }

    @UiField
    Label gameHeader;

    @UiField
    Label gameSubHeader;

    private Game game;
    
    public SingleGameView(Game game) {
        initWidget(uiBinder.createAndBindUi(this));
        this.game = game;
        gameHeader.setText(game.getOwnerEmail() + " : " + game.getDate());
        gameSubHeader.setText(getSubText());
    }



    @UiHandler("gameHeader")
    void onClick(ClickEvent e) {
        if (game.getOwnerEmail().equals(AppUtils.getUserEmail())) {
            DisplayStack.push(new GameTableView(game, false));
        } else {
            DisplayStack.push(new GameTableView(game, true));
        }
    }

    private String getSubText() {
        StringBuilder builder = new StringBuilder();
        for (String player : game.getPlayers()) {
            builder.append("  " + player + ", ");
        }
        return builder.toString();
    }

}
