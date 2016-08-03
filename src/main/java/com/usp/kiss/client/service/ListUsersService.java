package com.usp.kiss.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.usp.kiss.shared.model.UserInfo;

@RemoteServiceRelativePath("service.s3gwt")
public interface ListUsersService extends RemoteService {

    List<UserInfo> list();
}
