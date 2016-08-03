package com.usp.kiss.client.service;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.usp.kiss.shared.AuthException;
import com.usp.kiss.shared.NotPermittedException;

@RemoteServiceRelativePath("service.s3gwt")
public interface DeleteGameService extends RemoteService {

    void delete(Key gameKey) throws AuthException, NotPermittedException;
}
