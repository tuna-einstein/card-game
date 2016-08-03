package com.usp.kiss.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AggScoreChangedEvent extends GwtEvent<AggScoreChangedEventHandler> {

    public static Type<AggScoreChangedEventHandler> TYPE = new Type<AggScoreChangedEventHandler>();

    @Override
    public Type<AggScoreChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AggScoreChangedEventHandler handler) {
        handler.onAggScoreChanged(this);
    }
}

