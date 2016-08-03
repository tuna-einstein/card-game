package com.usp.kiss.server.service;

import org.slim3.datastore.Datastore;

import com.usp.kiss.client.service.UpdateEpisodeService;
import com.usp.kiss.shared.model.Episode;

public class UpdateEpisodeServiceImpl implements UpdateEpisodeService {

    public void update(Episode episode) {
       Datastore.put(episode);
    }

}
