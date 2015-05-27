package com.usp.kiss.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PlayerNameChangedEventHandler extends EventHandler {
    void onPlayerNameChange(PlayerNameChangedEvent event);
}
