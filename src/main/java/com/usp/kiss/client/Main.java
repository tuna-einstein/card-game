package com.usp.kiss.client;

import javax.annotation.Nonnull;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.usp.kiss.client.service.GetLoginEmailService;
import com.usp.kiss.client.service.GetLoginEmailServiceAsync;


public class Main implements EntryPoint {

    Image im = new Image();

    public void onModuleLoad() {

        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                GWT.log("Error:", e.getCause());
                ensureNotUmbrellaError(e);
            }
        });

        DisplayStack.push(BusyWidget.getLoading());
        checkLogin();
    }

    private static void ensureNotUmbrellaError(@Nonnull Throwable e) {
        e.printStackTrace();
        for (Throwable th : ((UmbrellaException) e).getCauses()) {
            GWT.log("Error:", th);
            if (th instanceof UmbrellaException) {
                ensureNotUmbrellaError(th);
            } else {
                System.err.println(th);
            }
        }
    }
    
    private void checkLogin() {
        GetLoginEmailServiceAsync emailfetcher = GWT.create(GetLoginEmailService.class);
        emailfetcher.getEmail( new AsyncCallback<String>() {

            public void onFailure(Throwable caught) {
                Window.Location.replace("/logoutURL");
            }

            public void onSuccess(String result) {
                if (result.contains("@")) {
                    AppUtils.setUserEmail(result);
                   // DisplayStack.pop();
                   DisplayStack.push(new MainDashBoard());
                } else {
                	initLogin();
                }
            }
        });
    }
    
    private void initLogin() {
        GetLoginEmailServiceAsync emailfetcher = GWT.create(GetLoginEmailService.class);
        emailfetcher.getEmail( new AsyncCallback<String>() {

            public void onFailure(Throwable caught) {
                Window.Location.replace("/logoutURL");
            }

            public void onSuccess(String result) {
                if (result.contains("@")) {
                    AppUtils.setUserEmail(result);
                   // DisplayStack.pop();
                  DisplayStack.push(new MainDashBoard());
                } else {
                    Window.open(result,  "_self", "");
                }
            }
        });
    }
}