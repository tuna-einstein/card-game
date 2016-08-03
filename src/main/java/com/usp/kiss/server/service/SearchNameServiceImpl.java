package com.usp.kiss.server.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.usp.kiss.client.service.SearchNameService;
import com.usp.kiss.server.meta.SearchPrefixMeta;
import com.usp.kiss.shared.model.SearchPrefix;

public class SearchNameServiceImpl implements SearchNameService {

    public List<SearchPrefix> search(String prefix) {
        SearchPrefixMeta prefixMeta = SearchPrefixMeta.get();
        return Datastore.query(prefixMeta)
                .filter(prefixMeta.prefix.startsWith(prefix.toLowerCase()))
                .sort(prefixMeta.prefix.asc)
                .limit(10)
                .asList();
    }
}
