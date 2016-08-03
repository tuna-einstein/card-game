package com.usp.kiss.server.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.usp.kiss.client.service.SearchGamesService;
import com.usp.kiss.server.meta.GameMeta;
import com.usp.kiss.shared.model.Game;

public class SearchGamesServiceImpl implements SearchGamesService {

    public List<Game> search(String playerName) {
        GameMeta meta = GameMeta.get();
        return Datastore.query(meta)
                .filterInMemory(meta.players.contains(playerName))
                .sort(meta.date.desc)
                .asList();
    }

}
