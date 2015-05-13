package com.usp.kiss.client;

import com.google.gwt.event.shared.GwtEvent;

public class NextEpisodeEvent extends GwtEvent<NextEpisodeEventHandler> {

    public static Type<NextEpisodeEventHandler> TYPE = new Type<NextEpisodeEventHandler>();

    private int widgetId;
    
    public NextEpisodeEvent(int widgetId) {
        this.setWidgetId(widgetId);
    }
    
    @Override
    public Type<NextEpisodeEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(NextEpisodeEventHandler handler) {
        handler.onNextEpisode(this);
    }

    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }
}
