package com.usp.kiss.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetLoginEmailServiceAsync {

    void getEmail(AsyncCallback<String> callback);

}
