package com.usp.kiss.client.service;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.usp.kiss.shared.model.Game;

@RemoteServiceRelativePath("service.s3gwt")
public interface UpdatePlayerNameService extends RemoteService {
    
    Game update(Key gameKey, String playerName, int index);

}
