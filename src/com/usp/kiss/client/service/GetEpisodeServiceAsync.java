package com.usp.kiss.client.service;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.Episode;

public interface GetEpisodeServiceAsync {

    void get(Key key, AsyncCallback<Episode> callback);

}
