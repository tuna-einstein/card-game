package com.usp.kiss.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.usp.kiss.client.service.GetLoginEmailService;
import com.usp.kiss.client.service.GetLoginEmailServiceAsync;
import com.usp.kiss.client.service.ListUsersService;
import com.usp.kiss.client.service.ListUsersServiceAsync;
import com.usp.kiss.shared.model.UserInfo;

public class Main implements EntryPoint {

    Image im = new Image();

    private String email;
    private MainDashBoard mainView;
    public void onModuleLoad() {
        mainView = new MainDashBoard();
        DisplayStack.push(mainView);
//        TabLayoutPanel p=new TabLayoutPanel(1.5,Unit.EM);
//        p.setWidth("500px");
//        p.setHeight("500px");
//        p.add(new HTML("this"),"[this]");
//        p.add(new HTML("that"),"[that]");
//        p.add(new HTML("the other"),"[the other]");
//        RootLayoutPanel rp=RootLayoutPanel.get();
//        rp.add(p);
//        im.setUrl("loading37.gif");
//        
//        //initLogin();
//        listUsers();
    }

    

    private void listUsers() {
        ListUsersServiceAsync service = GWT.create(ListUsersService.class);
        service.list(new AsyncCallback<List<UserInfo>>() {

            public void onFailure(Throwable caught) {
                Window.alert("Failed :" + caught);
            }

            public void onSuccess(List<UserInfo> result) {
                DisplayStack.push(new DashBoardView(result));
            }
        });
    }
}