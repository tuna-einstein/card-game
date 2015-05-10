package com.usp.kiss.server.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.usp.kiss.client.service.ListUsersService;
import com.usp.kiss.server.meta.UserInfoMeta;
import com.usp.kiss.shared.model.UserInfo;

public class ListUsersServiceImpl implements ListUsersService {

    public List<UserInfo> list() {
        return Datastore.query(UserInfoMeta.get()).asList();
    }
}
