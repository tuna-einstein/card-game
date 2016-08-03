package com.usp.kiss.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Episode;

public interface UpdateEpisodeServiceAsync {

    void update(Episode episode, AsyncCallback<Void> callback);

}
