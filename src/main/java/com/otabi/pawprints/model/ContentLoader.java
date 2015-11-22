package com.otabi.pawprints.model;

import com.otabi.scoutbook.ContentLoadTask;
import com.otabi.scoutbook.URLFactory;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stephen on 11/21/2015.
 */
public class ContentLoader {

    protected static final int MAX_THREADS = 12;

    protected static ExecutorService contentLoaderService = Executors.newFixedThreadPool(
            MAX_THREADS,
            new ContentLoadFactory()
    );

    public static void loadContent(URL url, ChangeListener<String> listener) {
        ContentLoadTask task = new ContentLoadTask(url);
        task.valueProperty().addListener(listener);
        contentLoaderService.submit(task);
    }

    static class ContentLoadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);

        public ContentLoadFactory () {
        }

        @Override public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "ContentLoaderService-" + poolNumber.getAndIncrement() + "-thread");
            thread.setDaemon(true);

            return thread;
        }
    }
}
