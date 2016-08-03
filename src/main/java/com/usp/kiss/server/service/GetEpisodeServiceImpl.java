package com.usp.kiss.server.service;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.usp.kiss.client.service.GetEpisodeService;
import com.usp.kiss.shared.model.Episode;

public class GetEpisodeServiceImpl implements GetEpisodeService {

    public Episode get(Key key) {
        return Datastore.get(Episode.class, key);
    }

}
