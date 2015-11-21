package com.otabi.scoutbook;

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
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String SCOUT_NAME = "/mobile/dashboard/admin/account.asp\\?ScoutUserID=(\\d+)\">\\s*(.+?)(?:\\s.*)*?(\\s+.).*?\\s*</a>";
    protected static final Pattern PATTERN = Pattern.compile(SCOUT_NAME);

    public static void getScouts(int unit, int den, final ScoutHandler handler) throws Exception {
        ContentLoadTask task = new ContentLoadTask(URLFactory.getDen(unit, den));
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent workerStateEvent) {
                Map<Integer, String> scoutMap = new HashMap<Integer, String>();
                Matcher matcher = PATTERN.matcher((String) workerStateEvent.getSource().getValue());
                int id;

                while (matcher.find()) {
                    id = Integer.parseInt(matcher.group(1));
                    String name = matcher.group(2) + ((matcher.groupCount() > 2) ? matcher.group(3) : "");

                    scoutMap.put(id, name);
                }

                handler.processScoutMap(scoutMap);
            }
        });
        new Thread(task).start();
    }
}
