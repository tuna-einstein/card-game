package com.usp.kiss.client;

import javax.annotation.Nonnull;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.ui.Image;


public class Main implements EntryPoint {

    Image im = new Image();

    private String email;
    private MainDashBoard mainView;
    public void onModuleLoad() {

        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                GWT.log("Error:", e.getCause());
                ensureNotUmbrellaError(e);
            }
        });

        mainView = new MainDashBoard();
        DisplayStack.push(mainView);

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
}