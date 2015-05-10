package com.usp.kiss.client;

import com.google.gwt.event.shared.EventHandler;

public interface ScoreUpdatedEventHandler extends EventHandler {
    void onScoreUpdated(ScoreUpdatedEvent event);
}
