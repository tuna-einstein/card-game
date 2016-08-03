package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.usp.kiss.shared.model.SearchPrefix;

public interface SearchNameServiceAsync {

    void search(String prefix, AsyncCallback<List<SearchPrefix>> callback);

}
