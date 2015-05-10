package com.usp.kiss.client;

import com.google.gwt.event.shared.GwtEvent;

public class ScoreUpdatedEvent extends GwtEvent<ScoreUpdatedEventHandler> {

    public static Type<ScoreUpdatedEventHandler> TYPE = new Type<ScoreUpdatedEventHandler>();

    @Override
    public Type<ScoreUpdatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ScoreUpdatedEventHandler handler) {
        handler.onScoreUpdated(this);
    }
}
