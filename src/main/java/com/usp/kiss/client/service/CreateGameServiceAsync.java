package com.usp.kiss.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Game;

public interface CreateGameServiceAsync {

    void create(int numPlayers, AsyncCallback<Game> callback);

}
