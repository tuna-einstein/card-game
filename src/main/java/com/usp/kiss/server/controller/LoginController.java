package com.usp.kiss.server.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.usp.kiss.server.common.CommonUtils;

public class LoginController extends Controller {

    @Override
    public Navigation run() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null) {
            CommonUtils.redirectToLoginPage();
            return null;
        }
//        UserInfoMeta e = UserInfoMeta.get();
//        UserInfo userInfo = Datastore.query(e)
//            .filter(e.userId.equal(user.getUserId()))
//            .asSingle();
//        if (userInfo == null) {
//            userInfo = new UserInfo();
//        }
//        userInfo.setDisplayName(user.getNickname());
//        userInfo.setUserId(user.getUserId());
//        userInfo.setEmail(user.getEmail());
//        Datastore.put(userInfo);
        return forward("index.html");
    }
}
