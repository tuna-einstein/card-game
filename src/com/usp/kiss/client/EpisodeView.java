package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.custom.EditableLabel;
import com.usp.kiss.client.service.UpdateEpisodeService;
import com.usp.kiss.client.service.UpdateEpisodeServiceAsync;
import com.usp.kiss.shared.model.Episode;

public class EpisodeView extends Composite {

    private static EpisodeViewUiBinder uiBinder = GWT
            .create(EpisodeViewUiBinder.class);

    interface EpisodeViewUiBinder extends UiBinder<Widget, EpisodeView> {
    }
    
    @UiField Label errorLabel;

    @UiField DisclosurePanel mainContainer;

    @UiField HorizontalPanel actualValueContainer;
    @UiField HorizontalPanel expectedValueContainer;
    @UiField HorizontalPanel scoreContainer;

    @UiField EditableLabel title;


    private boolean isReadonly;
    private EditableLabel[] expectedViews;
    private EditableLabel[] actualViews;
    private EditableLabel[] scoreViews;
    private boolean inProgress;
    private boolean needsUpdate;


    public @UiConstructor EpisodeView(final Episode episode, boolean isReadOnly) {
        initWidget(uiBinder.createAndBindUi(this));
        this.isReadonly = isReadOnly;
        int size = episode.getExpected().length;
        expectedViews = new EditableLabel[size];
        actualViews = new EditableLabel[size];
        scoreViews = new EditableLabel[size];
        title.setReadOnly(true);

        for (int i = 0; i < size; i++) {
            expectedViews[i] = new EditableLabel();
            expectedViews[i].setReadOnly(isReadOnly);
            expectedValueContainer.add(expectedViews[i]);

            actualViews[i] = new EditableLabel();
            actualViews[i].setReadOnly(isReadOnly);
            actualValueContainer.add(actualViews[i]);

            scoreViews[i] = new EditableLabel();
            scoreViews[i].setReadOnly(true);
            scoreContainer.add(scoreViews[i]);

        }
        addTextHandlers(size);

        setData(episode);
        
        mainContainer.addOpenHandler(new OpenHandler<DisclosurePanel>() {
            
            public void onOpen(OpenEvent<DisclosurePanel> event) {
                Timer timer = new Timer() {
                    @Override
                    public void run() {
                        updateData();
                        mainContainer.setOpen(false);
                    }
                };
                timer.schedule(30000);
            }
        });
    }

    private void addTextHandlers(int size) {
        for (int i = 0 ; i < size; i++ ) {
            final int index = i;
            expectedViews[i].addValueChangeHandler(new ValueChangeHandler<String>() {

                public void onValueChange(ValueChangeEvent<String> event) {
                    int[] updated = episode.getExpected();
                    try {
                        updated[index] = Integer.parseInt(event.getValue());
                        episode.setExpected(updated);
                        int updatedScore = AppUtils.getScore(updated[index], episode.getActual()[index]);
                        scoreViews[index].setText(String.valueOf(updatedScore));
                        sanityCheck();
                        updateData();
                    } catch(NumberFormatException e) {

                    }
                }
            });
            
            actualViews[i].addValueChangeHandler(new ValueChangeHandler<String>() {

                public void onValueChange(ValueChangeEvent<String> event) {
                    int[] updated = episode.getActual();
                    try {
                        updated[index] = Integer.parseInt(event.getValue());
                        episode.setActual(updated);
                        int updatedScore = AppUtils.getScore(updated[index], episode.getExpected()[index]);
                        scoreViews[index].setText(String.valueOf(updatedScore));
                        sanityCheck();
                        updateData();
                    } catch(NumberFormatException e) {

                    }
                    
                }
            });
        }
    }


  
    private Episode episode;
    public void setData(Episode epis) {
        this.episode = epis;
        title.setText(episode.getTitle());
        int[] expected = episode.getExpected();
        int[] actual = episode.getActual();
        int[] score = AppUtils.computeScore(episode);
        int size = expected.length;

        for (int i = 0; i < size; i++) {
            expectedViews[i].setText(String.valueOf(expected[i]));
            actualViews[i].setText(String.valueOf(actual[i]));
            scoreViews[i].setText(String.valueOf(score[i]));
        }
        AppUtils.EVENT_BUS.fireEvent(new ScoreUpdatedEvent());
        sanityCheck();
    }

    private void sanityCheck() {
        String title = episode.getTitle();

        int cardCount = Integer.parseInt(title.substring(0, title.length() - 1));
        int[] expected = episode.getExpected();
        int total = 0;
        int skip = 0;
        for (int v : expected) {
            if (v < 0) {
                skip++;
                continue;
            }
            total += v;
        }
        String message = ""; 
        if (skip != expected.length && cardCount == total) {
            message = "Error : Count mismatch in expected.";
        }

        int[] actual = episode.getActual();
        total = 0;
        skip = 0;
        for (int v : actual) {
            if (v < 0) {
                skip++;
                continue;
            }
            total += v;
        }
        if (skip != actual.length && cardCount != total) {
            if (!message.isEmpty()) {
                message = message + " And ";
            } else {
                message = "Error : ";
            }
            message = message + " Count mismatch in actual.";
        } 
        errorLabel.setText(message);
    }

    private void updateData() {
        AppUtils.EVENT_BUS.fireEvent(new ScoreUpdatedEvent());
        needsUpdate = true;
        if (inProgress) {
            return;
        }
        
        needsUpdate = false;
        inProgress = true;
        UpdateEpisodeServiceAsync service = GWT.create(UpdateEpisodeService.class);
        service.update(episode, new AsyncCallback<Void>() {

            public void onFailure(Throwable caught) {
                inProgress = false;
                Window.alert("Error :" + caught.getMessage());
                if (needsUpdate) {
                    updateData();
                }
            }

            public void onSuccess(Void result) {
                inProgress = false;
                
                if (needsUpdate) {
                    updateData();
                }
            }
        });
    }
}
