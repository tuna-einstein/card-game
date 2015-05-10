package com.usp.kiss.server.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.usp.kiss.client.service.ListEpisodesService;
import com.usp.kiss.server.meta.EpisodeMeta;
import com.usp.kiss.shared.model.Episode;

public class ListEpisodesServiceImpl implements ListEpisodesService {

    public List<Episode> list(long gameId) {
        EpisodeMeta meta = EpisodeMeta.get();
        return Datastore.query(meta)
                .filter(meta.gameId.equal(gameId))
                .asList();
    }

}
