package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Game;

public interface SearchGamesServiceAsync {

    void search(String playerNamePrefix, AsyncCallback<List<Game>> callback);

    
}
