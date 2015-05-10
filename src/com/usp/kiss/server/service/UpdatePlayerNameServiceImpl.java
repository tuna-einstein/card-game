package com.usp.kiss.server.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.usp.kiss.client.service.UpdatePlayerNameService;
import com.usp.kiss.server.meta.SearchPrefixMeta;
import com.usp.kiss.shared.model.Game;
import com.usp.kiss.shared.model.SearchPrefix;

public class UpdatePlayerNameServiceImpl implements UpdatePlayerNameService {

    public Game update(Key gameKey, String playerName, int index) {
        Game game = Datastore.get(Game.class, gameKey);
        game.getPlayers().set(index, playerName.toLowerCase());
        Datastore.put(game);
        
        // Update prefix store
        SearchPrefixMeta meta = SearchPrefixMeta.get();
        List<SearchPrefix> prefixes = Datastore.query(meta)
                .filter(meta.prefix.equal(playerName))
                .asList();
        if (prefixes.isEmpty()) {
            SearchPrefix newPrefix = new SearchPrefix();
            newPrefix.setPrefix(playerName.toLowerCase());
            Datastore.put(newPrefix);
        }
        
        return game;
    }

}
