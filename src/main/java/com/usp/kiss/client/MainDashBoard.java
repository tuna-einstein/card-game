package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.service.GetLoginEmailService;
import com.usp.kiss.client.service.GetLoginEmailServiceAsync;

public class MainDashBoard extends Composite {

    private static MainDashBoardUiBinder uiBinder = GWT
            .create(MainDashBoardUiBinder.class);

    interface MainDashBoardUiBinder extends UiBinder<Widget, MainDashBoard> {
    }

    @UiField  TabLayoutPanel  tabPanel;
    @UiField Image search;
    @UiField Image list;
    @UiField Image create;
    @UiField Label loginName;
    @UiField SingleUserView singleUserView;

    private String userEmail;
    public MainDashBoard() {
        initWidget(uiBinder.createAndBindUi(this));
        checkLogin();
        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {

            public void onSelection(SelectionEvent<Integer> event) {
                switch(event.getSelectedItem()) {
                    case 0:
                        list.setUrl("list_unselected.png");
                        search.setUrl("search.png");
                        create.setUrl("create.png");
                        if (userEmail == null) {
                            initLogin();
                        } else {
                            singleUserView.listGames(userEmail);
                        }
                        break;
                    case 1:
                        create.setUrl("create_unselected.png");
                        search.setUrl("create.png");
                        list.setUrl("list.png");
                        break;

                    case 2:
                        search.setUrl("search_unselected.png");
                        list.setUrl("list.png");
                        create.setUrl("create.png");
                        break;
                }

            }
        });
        tabPanel.selectTab(0);
        if (userEmail == null) {
            initLogin();
        } else {
            singleUserView.listGames(userEmail);
        }
    }

    private void initLogin() {
        GetLoginEmailServiceAsync emailfetcher = GWT.create(GetLoginEmailService.class);
        emailfetcher.getEmail( new AsyncCallback<String>() {

            public void onFailure(Throwable caught) {
                Window.Location.replace("/logoutURL");
            }

            public void onSuccess(String result) {
                if (result.contains("@")) {
                    userEmail = result;
                    AppUtils.setUserEmail(result);
                    loginName.setText(result);
                    singleUserView.listGames(result);
                } else {
                    Window.open(result,  "_self", "");
                }
            }
        });
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
                    loginName.setText(result);
                } else {
                    // Do nothing.
                }
            }
        });
    }
}
