package com.usp.kiss.client.service;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Game;

public interface UpdatePlayerNameServiceAsync {

    void update(Key gameKey, String playerName, int index,
            AsyncCallback<Game> callback);

}
