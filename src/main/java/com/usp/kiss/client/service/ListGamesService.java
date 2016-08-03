package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.usp.kiss.shared.model.Game;

@RemoteServiceRelativePath("service.s3gwt")
public interface ListGamesService extends RemoteService {

    List<Game> list(String userId);
}
