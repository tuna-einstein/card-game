package com.usp.kiss.client;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.custom.EditableLabel;
import com.usp.kiss.client.service.DeleteGameService;
import com.usp.kiss.client.service.DeleteGameServiceAsync;
import com.usp.kiss.client.service.ListEpisodesService;
import com.usp.kiss.client.service.ListEpisodesServiceAsync;
import com.usp.kiss.client.service.UpdatePlayerNameService;
import com.usp.kiss.client.service.UpdatePlayerNameServiceAsync;
import com.usp.kiss.shared.model.Episode;
import com.usp.kiss.shared.model.Game;

public class GameTableView extends Composite  {

    private static GameTableViewUiBinder uiBinder = GWT
            .create(GameTableViewUiBinder.class);

    interface GameTableViewUiBinder extends UiBinder<Widget, GameTableView> {
    }

    @UiField
    VerticalPanel episodeContainer;
    @UiField
    HorizontalPanel playerContainer;
    @UiField
    HorizontalPanel aggScoreContainer;

    @UiField Image deleteButton;

    @UiField
    Label header;

    List<Episode> episodes;

    @UiField EditableLabel epsLabel;


    private Game game;
    private EditableLabel[] playerNames;
    private EditableLabel[] aggScores;
    private boolean isReadOnly;

    public GameTableView(Game gam, boolean isReadOnly) {
        this.game = gam;
        this.isReadOnly = isReadOnly;
        initWidget(uiBinder.createAndBindUi(this));
        fetchEpisodes();

        deleteButton.setVisible(!isReadOnly);

        epsLabel.setValue("Eps");
        epsLabel.getElement().getStyle().setWidth(90, Unit.PCT);
        playerNames = new EditableLabel[game.getNumPlayers()];
        List<String> names = game.getPlayers();
        for (int i = 0; i < game.getNumPlayers(); i++) {
            final int index = i;
            playerNames[i] = new EditableLabel();
            playerNames[i].setReadOnly(isReadOnly);
            playerNames[i].getElement().getStyle().setWidth(90, Unit.PCT);
            playerNames[i].setValue(names.get(i));
            playerContainer.add(playerNames[i]);
            playerNames[i].addValueChangeHandler(new ValueChangeHandler<String>() {

                public void onValueChange(ValueChangeEvent<String> event) {
                    playerNames[index].setFocus(false);
                    UpdatePlayerNameServiceAsync service = GWT.create(UpdatePlayerNameService.class);
                    service.update(game.getKey(), event.getValue(), index,
                        new AsyncCallback<Game>() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error:" + caught.getMessage());

                        }

                        public void onSuccess(Game result) {
                            game = result;
                        }
                    });

                }
            });
        }

        header.setText(game.getDate().toString());
        header.getElement().getStyle().setColor("white");
        aggScores = new EditableLabel[game.getNumPlayers()];

        EditableLabel placeHolder = new EditableLabel();
        placeHolder.getElement().getStyle().setVisibility(Visibility.HIDDEN);
        aggScoreContainer.add(placeHolder);

        for (int i = 0; i < game.getNumPlayers(); i++) {
            aggScores[i] = new EditableLabel();
            aggScores[i].setReadOnly(true);
            aggScoreContainer.add(aggScores[i]);
        }

        AppUtils.EVENT_BUS.addHandler(ScoreUpdatedEvent.TYPE, new ScoreUpdatedEventHandler() {

            public void onScoreUpdated(ScoreUpdatedEvent event) {
                int[] scores = null;
                for (Episode episode : episodes) {
                    int score[] = AppUtils.computeScore(episode);
                    if (scores == null) {
                        scores = new int[score.length];
                    }
                    for (int j = 0; j < scores.length; j++) {
                        scores[j] += score[j];
                    }
                }
                int min = minScore(scores);
                int max = maxScore(scores);
                Integer sorted[] = new Integer[scores.length];
                for (int i = 0; i < scores.length; i++) {
                    sorted[i] = scores[i];
                }
                Arrays.sort(sorted, new Comparator<Integer>() {

                    public int compare(Integer o1, Integer o2) {
                        if (o1 < o2) {
                            return 1;
                        }
                        return -1;
                    }

                });

                for (int i = 0; i < scores.length; i++ ) {
                    aggScores[i].setText(String.valueOf(scores[i]));
                    aggScores[i].getElement().getStyle().setBackgroundColor("teal");
                    if (sorted.length > 3 && scores[i] == sorted[2]) {

                        aggScores[i].getElement().getStyle().setBackgroundColor("rgb(178, 150, 39)");
                    }
                    if (sorted.length > 2 && scores[i] == sorted[1]) {
                        aggScores[i].getElement().getStyle().setBackgroundColor("rgb(108, 11, 140)");
                    }
                    if(sorted.length > 1 && scores[i] == sorted[0]) {
                        aggScores[i].getElement().getStyle().setBackgroundColor("rgb(56, 184, 58)");
                    }
                }
            }
        });

    }

    private void fetchEpisodes() {
        episodeContainer.clear();
        episodeContainer.add(new Label("Plesase Wait....."));
        // DisplayStack.push(BusyWidget.get());
        ListEpisodesServiceAsync service = GWT.create(ListEpisodesService.class);
        service.list(game.getKey().getId(), new AsyncCallback<List<Episode>>() {

            public void onSuccess(List<Episode> result) {
                episodes = result;
                episodeContainer.clear();
                AppUtils.EVENT_BUS.fireEvent(new ScoreUpdatedEvent());
                for (Episode episode : result) {
                    episodeContainer.add(new EpisodeView(episode, isReadOnly));
                }
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error :" + caught);
            }
        });
    }

    @UiHandler("homeButton")
    void handleClick(ClickEvent e) {
        Window.Location.reload();
    }

    @UiHandler("deleteButton")
    void deleteClick(ClickEvent e) {
        DisplayStack.push(BusyWidget.get());
        DeleteGameServiceAsync service = GWT.create(DeleteGameService.class);
        service.delete(game.getKey(), new AsyncCallback<Void>() {

            public void onFailure(Throwable caught) {
                Window.alert("Error :" + caught.getMessage());
                // Remove the busywidget
                DisplayStack.pop();


            }

            public void onSuccess(Void result) {
                Window.Location.reload();
            }
        });
    }

    @UiHandler("refreshButton")
    void refreshClick(ClickEvent e) {
        fetchEpisodes();
    }

    private String getColor(double colorIndex) {
        StringBuilder sb = new StringBuilder();
        int index = (int) (colorIndex * 100);
        sb.append("#0000" + Integer.toHexString(index));
        return "hsl(277," + index + "%,60%)";
    }

    private int minScore(int[] scores) {

        int min = scores[0];
        for (int score : scores) {
            if (min > score) {
                min = score;
            }
        }
        return min;
    }


    private int maxScore(int[] scores) {
        int max = scores[0];
        for (int score : scores) {
            if (max < score) {
                max = score;
            }
        }
        return max;
    }



}