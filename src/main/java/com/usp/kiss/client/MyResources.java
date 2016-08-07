package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by umasankar on 8/6/16.
 */

public interface MyResources extends ClientBundle {
    public static final MyResources INSTANCE =  GWT.create(MyResources.class);


    @Source("create_white.png")
    public ImageResource createWhite();
}
