package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Game;

public interface ListGamesServiceAsync {

    void list(String userEmail, AsyncCallback<List<Game>> callback);

}
