package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.custom.EditableLabel;
import com.usp.kiss.client.event.PlayerNameChangedEvent;
import com.usp.kiss.client.event.PlayerNameChangedEventHandler;
import com.usp.kiss.client.service.UpdateEpisodeService;
import com.usp.kiss.client.service.UpdateEpisodeServiceAsync;
import com.usp.kiss.shared.model.Episode;

public class EpisodeView extends Composite {

    private static EpisodeViewUiBinder uiBinder = GWT
            .create(EpisodeViewUiBinder.class);

    interface EpisodeViewUiBinder extends UiBinder<Widget, EpisodeView> {
    }

    @UiField HTML errorLabel;
    @UiField HTML dealerNameLabel;

    @UiField DisclosurePanel mainContainer;

    @UiField HorizontalPanel actualValueContainer;
    @UiField HorizontalPanel expectedValueContainer;
    @UiField HorizontalPanel scoreContainer;

    @UiField EditableLabel titleLabel;


    private boolean isReadonly;
    private EditableLabel[] expectedViews;
    private EditableLabel[] actualViews;
    private EditableLabel[] scoreViews;
    private boolean inProgress;
    private boolean needsUpdate;
    private int widgetId;
    private final ExpectedScoreBoardView expectedScoreBoardView = new ExpectedScoreBoardView();

    public @UiConstructor EpisodeView(final Episode episode, boolean isReadOnly, int id) {
        initWidget(uiBinder.createAndBindUi(this));
        this.isReadonly = isReadOnly;
        this.widgetId = id;
        int size = episode.getExpected().length;
        expectedViews = new EditableLabel[size];
        actualViews = new EditableLabel[size];
        scoreViews = new EditableLabel[size];
        titleLabel.setReadOnly(true);
        dealerNameLabel.setText("Dealer : " + AppUtils.getDealer(id));
        AppUtils.EVENT_BUS.addHandler(PlayerNameChangedEvent.TYPE, new PlayerNameChangedEventHandler() {

            public void onPlayerNameChange(PlayerNameChangedEvent event) {
                dealerNameLabel.setText("Dealer : " + AppUtils.getDealer(widgetId));
            }
        });

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
        if (!isReadOnly) {
            AppUtils.EVENT_BUS.addHandler(NextEpisodeEvent.TYPE, new NextEpisodeEventHandler() {

                public void onNextEpisode(NextEpisodeEvent event) {
                    if (event.getWidgetId() == widgetId) {
                        mainContainer.setOpen(true);
                        mainContainer.getElement().getStyle().setBackgroundColor("rgb(227, 242, 216)");
                        expectedViews[0].setFocus(true);
                        dealerNameLabel.setText("Dealer : " + AppUtils.getDealer(widgetId));
                    } else {
                        mainContainer.getElement().getStyle().setBackgroundColor("white");
                    }
                }
            });
        }

        addTextHandlers(size);

        setData(episode);

        mainContainer.addCloseHandler(new CloseHandler<DisclosurePanel>() {

            public void onClose(CloseEvent<DisclosurePanel> event) {
                errorLabel.setHTML("");
                dealerNameLabel.setHTML("");
            }
        });
        mainContainer.addOpenHandler(new OpenHandler<DisclosurePanel>() {

            public void onOpen(OpenEvent<DisclosurePanel> event) {
                dealerNameLabel.setText("Dealer : " + AppUtils.getDealer(widgetId));
                sanityCheck();
            }
        });
    }

    private void addTextHandlers(final int size) {
        for (int i = 0 ; i < size; i++ ) {
            final int index = i;

            expectedViews[i].addKeyUpHandler(new KeyUpHandler() {

                public void onKeyUp(KeyUpEvent event) {
                    int keyCode = event.getNativeEvent().getKeyCode();
                    if(keyCode >= 48 && keyCode <=57) {
                        if (index < size -1) {
                            expectedViews[index + 1].setFocus(true);
                        } else {
                            actualViews[0].setFocus(true);
                        }
                    }   

                }
            });

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
                        if (index == size - 1) {
                            expectedScoreBoardView.setExpected(episode.getExpected(), episode.getTitle(), widgetId);
                            expectedScoreBoardView.center();
                        }
                    } catch(NumberFormatException e) {

                    }
                }
            });

            actualViews[i].addKeyUpHandler(new KeyUpHandler() {

                public void onKeyUp(KeyUpEvent event) {
                    int keyCode = event.getNativeEvent().getKeyCode();
                    if(keyCode >= 48 && keyCode <=57) {
                        if (index < size -1) {
                            actualViews[index + 1].setFocus(true);
                        } else {
                            // Create a new timer that calls Window.alert().
                            actualViews[index].setFocus(false);
                            Timer t = new Timer() {
                                @Override
                                public void run() {
                                    mainContainer.setOpen(false);
                                    AppUtils.EVENT_BUS.fireEvent(new NextEpisodeEvent(widgetId + 1));
                                }
                            };
                            t.schedule(10000);
                        }
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
        titleLabel.setText(episode.getTitle());
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
        int expectedTotal = total;
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

        errorLabel.setHTML(message);
        if (!mainContainer.isOpen()) {
            errorLabel.setText("");
        }
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

    @UiHandler("enlargeButton")
    public void onClickEnlargeButton(ClickEvent e) {
        expectedScoreBoardView.setExpected(episode.getExpected(), episode.getTitle(), widgetId);
        expectedScoreBoardView.center();
    }
}
