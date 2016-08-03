package com.usp.kiss.client.event;

import com.google.gwt.event.shared.GwtEvent;



public class PlayerNameChangedEvent extends GwtEvent<PlayerNameChangedEventHandler> {

    public static Type<PlayerNameChangedEventHandler> TYPE = new Type<PlayerNameChangedEventHandler>();

    @Override
    public Type<PlayerNameChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayerNameChangedEventHandler handler) {
        handler.onPlayerNameChange(this);
    }
}
