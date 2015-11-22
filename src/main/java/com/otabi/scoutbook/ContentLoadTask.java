package com.otabi.scoutbook;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by Stephen on 11/17/2015.
 */
public class ContentLoadTask extends Task<String> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final URL url;
    protected static BooleanProperty loadingProperty = new SimpleBooleanProperty(false);

    public static Boolean getLoadingProperty() {
        return loadingProperty.get();
    }

    public static ObservableBooleanValue loadingPropertyProperty() {
        return loadingProperty;
    }

    public ContentLoadTask(URL url) {
        this.url = url;
    }

    @Override
    protected String call() throws Exception {
        if (url == null) {
            throw new IllegalArgumentException("No URL set in when loading content.");
        }
        return Session.getInstance().getContent(url);
    }
}
