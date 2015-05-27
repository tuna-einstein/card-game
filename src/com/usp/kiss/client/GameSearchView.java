package com.usp.kiss.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.event.RefreshDataEvent;
import com.usp.kiss.client.event.RefreshDataEventHandler;
import com.usp.kiss.client.service.SearchGamesService;
import com.usp.kiss.client.service.SearchGamesServiceAsync;
import com.usp.kiss.client.service.SearchNameService;
import com.usp.kiss.client.service.SearchNameServiceAsync;
import com.usp.kiss.shared.model.Game;
import com.usp.kiss.shared.model.SearchPrefix;

public class GameSearchView extends Composite {

    private static GameSearchViewUiBinder uiBinder = GWT
            .create(GameSearchViewUiBinder.class);

    interface GameSearchViewUiBinder extends UiBinder<Widget, GameSearchView> {
    }


    @UiField (provided = true) SuggestBox suggestBox;
    @UiField Image search;
    @UiField VerticalPanel resultContainer;

    private boolean isBusy;
    private String previousText;

    MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

    private String searchString = "";

    public @UiConstructor GameSearchView() {
        suggestBox = new SuggestBox(oracle);
        initWidget(uiBinder.createAndBindUi(this));
        AppUtils.EVENT_BUS.addHandler(RefreshDataEvent.TYPE, new RefreshDataEventHandler() {

            public void refreshData(RefreshDataEvent event) {
                if (isAttached() && !searchString.isEmpty()) {
                    suggestBox.setText(searchString);
                    search();
                }
            }
        });
    }

    @UiHandler("search")
    public void onSearch(ClickEvent event) {
       
        search();
    }

    private void search() {
        Label label = new Label("Please wait...");
        resultContainer.clear();
        resultContainer.add(label);
        SearchGamesServiceAsync service = GWT.create(SearchGamesService.class);
        searchString = suggestBox.getText();
        service.search(suggestBox.getText(), new AsyncCallback<List<Game>>() {

            public void onFailure(Throwable caught) {
                resultContainer.clear();
                Window.alert(caught.getMessage());
            }

            public void onSuccess(List<Game> result) {
                resultContainer.clear();
                if (result.isEmpty()) {
                    resultContainer.add(new Label("Opps...No results found"));
                    return;
                }
                for (Game game : result) {
                    resultContainer.add(new SingleGameView(game));
                }
            }
        });
    }

    @UiHandler("suggestBox")
    public void onTextAddFinish(ValueChangeEvent<String> event) {

    }

    @UiHandler("suggestBox")
    public void onTextAdd(KeyUpEvent event) {
        String newText = suggestBox.getText();
        if (newText.isEmpty()) {
            previousText = newText;
            return;
        }
        if (isBusy) {
            return;
        }
        isBusy = true;
        if (newText.equals(previousText)) {
            return;
        }
        previousText = newText;
        SearchNameServiceAsync service = GWT.create(SearchNameService.class);
        service.search(newText, new AsyncCallback<List<SearchPrefix>>() {

            public void onSuccess(List<SearchPrefix> result) {
                oracle.clear();
                for (SearchPrefix prefix : result) {
                    oracle.add(prefix.getPrefix());

                }
                isBusy = false;
            }

            public void onFailure(Throwable caught) {
                isBusy = false;
            }
        });

    }
}
