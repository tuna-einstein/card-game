package com.usp.kiss.server.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.usp.kiss.client.service.ListGamesService;
import com.usp.kiss.server.meta.GameMeta;
import com.usp.kiss.shared.model.Game;

public class ListGamesServiceImpl implements ListGamesService {

    public List<Game> list(String userEmail) {
        GameMeta gameMeta = GameMeta.get();
        return Datastore.query(gameMeta)
                .filter(gameMeta.ownerEmail.equal(userEmail))
                .sort(gameMeta.date.desc)
                .asList();
    }
}
