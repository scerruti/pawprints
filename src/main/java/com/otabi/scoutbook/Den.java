package com.otabi.scoutbook;

import com.otabi.pawprints.model.ContentLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/15/2015.
 */
public class Den {
    final static Logger logger = LoggerFactory.getLogger(Den.class);

    protected static final String SCOUT_NAME = "/mobile/dashboard/admin/account.asp\\?ScoutUserID=(\\d+)\">\\s*(.+?)(?:\\s.*)*?(\\s+.).*?\\s*</a>";
    protected static final Pattern PATTERN = Pattern.compile(SCOUT_NAME);

    public static void getScouts(int unit, int den, final ScoutHandler handler) throws Exception {
        logger.debug("getting scouts for unit {} den {}", unit, den);
        ContentLoader.loadContent(URLFactory.getDen(unit, den), new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue != null) {
                    Map<Integer, String> scoutMap = new HashMap<Integer, String>();
                    Matcher matcher = PATTERN.matcher((String) newValue);
                    int id;

                    while (matcher.find()) {
                        id = Integer.parseInt(matcher.group(1));
                        String name = matcher.group(2) + ((matcher.groupCount() > 2) ? matcher.group(3) : "");

                        scoutMap.put(id, name);
                    }

                    logger.debug("found {} scouts", scoutMap.size());
                    handler.processScoutMap(scoutMap);
                }
            }
        });
    }
}
