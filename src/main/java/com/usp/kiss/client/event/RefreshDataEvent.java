package com.usp.kiss.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RefreshDataEvent extends GwtEvent<RefreshDataEventHandler> {

    public static Type<RefreshDataEventHandler> TYPE = new Type<RefreshDataEventHandler>();

    @Override
    public Type<RefreshDataEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RefreshDataEventHandler handler) {
        handler.refreshData(this);
    }
}

