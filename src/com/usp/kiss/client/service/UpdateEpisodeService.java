package com.usp.kiss.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.usp.kiss.shared.model.Episode;

@RemoteServiceRelativePath("service.s3gwt")
public interface UpdateEpisodeService extends RemoteService {
    
    void update(Episode episode);

}
