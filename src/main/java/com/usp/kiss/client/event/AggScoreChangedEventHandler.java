package com.usp.kiss.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AggScoreChangedEventHandler extends EventHandler {
    void onAggScoreChanged(AggScoreChangedEvent event);
}

