package com.otabi.scoutbook;

import com.otabi.pawprints.model.ContentLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/21/2015.
 */
public class Dashboard {
    final static Logger logger = LoggerFactory.getLogger(Dashboard.class);
    protected static final String DASHBOARD_PATTERN =
            "data-rosterpending=\"(?<rosterPending>false|true)\" data-unitid=\"(?<unitId>\\d+?)\" data-unitdesc=\"(?<unitDesc>.+?) from the";

    protected static final Pattern PATTERN = Pattern.compile(DASHBOARD_PATTERN, Pattern.DOTALL|Pattern.CASE_INSENSITIVE);

    public static void getUnits(final UnitHandler handler) throws Exception {
        ContentLoader.loadContent(URLFactory.getDashboard(), new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue != null) {
                    ArrayList<Unit> unitList = new ArrayList<Unit>();

                    Matcher matcher = PATTERN.matcher(newValue);
                    while (matcher.find()) {
                        unitList.add(new Unit(
                                matcher.group("unitDesc"),
                                Integer.parseInt(matcher.group("unitId")),
                                Boolean.parseBoolean(matcher.group("rosterPending"))));
                    }

                    handler.processUnitList(unitList);
                }
            }
        });
    }
}
