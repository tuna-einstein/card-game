package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.UserInfo;

public interface ListUsersServiceAsync {

    void list(AsyncCallback<List<UserInfo>> callback);

}
