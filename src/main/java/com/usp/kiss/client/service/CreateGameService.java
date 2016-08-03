package com.usp.kiss.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.usp.kiss.shared.AuthException;
import com.usp.kiss.shared.InvalidValueException;
import com.usp.kiss.shared.model.Game;

@RemoteServiceRelativePath("service.s3gwt")
public interface CreateGameService extends RemoteService {

    Game create(int numPlayers) throws AuthException, InvalidValueException;
}
