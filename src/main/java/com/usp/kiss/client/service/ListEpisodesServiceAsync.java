package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Episode;

public interface ListEpisodesServiceAsync {

    void list(long gameId, AsyncCallback<List<Episode>> callback);

}
