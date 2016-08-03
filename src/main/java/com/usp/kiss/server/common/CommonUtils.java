package com.usp.kiss.server.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slim3.util.RequestLocator;
import org.slim3.util.ResponseLocator;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CommonUtils {

    public static String getOwnerEmail() {
        HttpServletRequest request = RequestLocator.get();
        if (request.getUserPrincipal() != null) {
            String ownerEmail = request.getUserPrincipal().getName();
            if (ownerEmail.contains("@")) {
                return ownerEmail;
            }
        }
        return null;
    }

    public static void redirectToLoginPage() throws IOException {
        HttpServletRequest request = RequestLocator.get();
        HttpServletResponse response = ResponseLocator.get();
        request.getSession().setAttribute("logoutFromGmail", "yes");
        UserService userService = UserServiceFactory.getUserService();
        response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
    }

    public static String getGoogleLoginUrl() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.createLoginURL("/login");
    }
}
