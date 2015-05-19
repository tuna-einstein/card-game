package com.usp.kiss.client;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.moxieapps.gwt.highcharts.client.Chart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.chart.GameChart;
import com.usp.kiss.client.chart.HitChart;
import com.usp.kiss.client.chart.IndividualChart;
import com.usp.kiss.client.chart.HitChart.Hit;
import com.usp.kiss.client.chart.ScoreChart;
import com.usp.kiss.client.chart.ScoreChart.PlayerScore;
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
    @UiField Image graphButton;

    @UiField
    Label header;

    List<Episode> episodes;

    @UiField EditableLabel epsLabel;


    private Game game;
    private EditableLabel[] playerNames;
    private EditableLabel[] aggScores;
    private boolean isReadOnly;
    private int openEpisodeEventId = 0;

    public GameTableView(Game gam, boolean isReadOnly) {
        this.game = gam;
        this.isReadOnly = isReadOnly;
        initWidget(uiBinder.createAndBindUi(this));
        if (!isReadOnly) {
            AppUtils.EVENT_BUS.addHandler(NextEpisodeEvent.TYPE, new NextEpisodeEventHandler() {

                public void onNextEpisode(NextEpisodeEvent event) {
                    openEpisodeEventId = event.getWidgetId();
                }
            });
        }

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
                int widgetId = 0;
                for (Episode episode : result) {
                    episodeContainer.add(new EpisodeView(episode, isReadOnly, widgetId));
                    widgetId ++;
                }
                AppUtils.EVENT_BUS.fireEvent(new NextEpisodeEvent(openEpisodeEventId));
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

    @UiHandler("graphButton")
    void graphClick(ClickEvent e) {
        ScoreChart scoreChart = new ScoreChart();
        Hit[] hits = new Hit[playerNames.length];
        int index = 0;
        
        for (EditableLabel playerName : playerNames) {

            PlayerScore playerScore = new PlayerScore()
            .setPlayerName(playerName.getText())
            .setScores(getChartScore(index));
            scoreChart.addPlayer(playerScore);
            
            hits[index] = new Hit();
            hits[index].setPlayerName(playerName.getText());
            
            index += 1;
        }

        for (Episode episode : episodes) {
            int[] expected = episode.getExpected();
            int[] actual = episode.getActual();
            int[] score = AppUtils.computeScore(episode);
            for (int i = 0; i < expected.length; i++) {
                if (expected[i] == actual[i] && expected[i] > 0) {
                    hits[i].incrementHitCountByOne();
                    hits[i].incrementScore(score[i]);
                }
            }
        }
        
        FaceBoardView view = new FaceBoardView();
        view.addContent(scoreChart.createChart());
        view.addContent(new HitChart().createChart(hits));
        for (int i = 0; i < playerNames.length; i++) {
            view.addContent(new IndividualChart().createChart(getIndividualData(i)));
        }
        
        RootPanel.get().clear();
        RootPanel.get().add(view);       
    }

    private Number[] getChartScore(int index) {
        Number[] result = new Number[20];
        int count = 0;
        for (Episode episode : episodes) {
            if (episode.getExpected()[index] < 0) {
                break;
            }
            int score = AppUtils.getScore(episode.getExpected()[index], episode.getActual()[index]);
            if (count == 0) {
                result[count] = score;
            } else {
                result[count] = result[count - 1].intValue() + score;
            }
            count += 1;
        }
        return result;
    }
    
    private IndividualChart.Data getIndividualData(int index) {
        IndividualChart.Data data = new IndividualChart.Data();
        data.setName(playerNames[index].getText());
        int c = 0;
        data.setExpected(new Number[20]);
        data.setActual(new Number[20]);
        data.setScore(new Number[20]);
        
        
        for (Episode episode : episodes) {
            int exp = episode.getExpected()[index];
            int act = episode.getActual()[index];
            int score = AppUtils.getScore(exp, act);
            if (exp < 0) {
                break;
            }
            data.getActual()[c] = act;
            data.getExpected()[c] = exp;
            if (c == 0) {
                data.getScore()[c] = score;
            } else {
                data.getScore()[c] = data.getScore()[c - 1].intValue() + score;
            }
            c++;
        }
        
        return data;
        
        
    }
}