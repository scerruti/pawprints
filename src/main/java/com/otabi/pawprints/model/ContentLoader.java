package com.otabi.pawprints.model;

import com.otabi.scoutbook.ContentLoadTask;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stephen on 11/21/2015.
 */
public class ContentLoader {

    protected static final int MAX_THREADS = 6;

    protected static ExecutorService contentLoaderService = Executors.newFixedThreadPool(
            MAX_THREADS,
            new ContentLoadFactory()
    );
    protected static Property<Number> loaderBurden = new SimpleDoubleProperty();
    private static BooleanProperty activeProperty = new SimpleBooleanProperty();

    static {
        new Timer("contentLoadMonitor", true).schedule(new TimerTask() {
            @Override
            public void run() {
                int activeThreads = ((ThreadPoolExecutor) contentLoaderService).getActiveCount();
                loaderBurden.setValue((double) activeThreads / MAX_THREADS);
                activeProperty.setValue(activeThreads > 0);
                return;
            }
        }, 0, 200);
    }

    public static void loadContent(URL url, ChangeListener<String> listener) {
        ContentLoadTask task = new ContentLoadTask(url);
        task.valueProperty().addListener(listener);
        contentLoaderService.submit(task);
    }

    public static Property<Number> getLoaderBurden() {
        return loaderBurden;
    }

    public static ObservableValue<? extends Boolean> getActiveProperty() {
        return activeProperty;
    }

    public static void cancelTimer() {

    }

    static class ContentLoadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);

        public ContentLoadFactory () {
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "ContentLoaderService-" + poolNumber.getAndIncrement() + "-thread");
            thread.setDaemon(true);

            return thread;
        }
    }
}
