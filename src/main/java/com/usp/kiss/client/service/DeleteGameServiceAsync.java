package com.usp.kiss.client.service;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DeleteGameServiceAsync {

    void delete(Key gameKey, AsyncCallback<Void> callback);

}
