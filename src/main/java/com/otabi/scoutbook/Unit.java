package com.otabi.scoutbook;

import com.otabi.pawprints.model.ContentLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/21/2015.
 */
public class Unit {
    final static Logger logger = LoggerFactory.getLogger(Unit.class);

    protected static final String DEN_ID = "li class=\"ui-icon-alt\".*?<a href=\"/mobile/dashboard/admin/denpatrol.asp\\?UnitID=\\d+&DenID=(?<denId>\\d+)";
//    protected static final String DEN_ID = "/mobile/dashboard/admin/denpatrol.asp\\?UnitID=\\d+&DenID=(?<denId>\\d+)";
    protected static final String DEN_NAME = "<div style=\"margin-left: 30px; \" class=\"noellipsis\">\\s+?(?<denName>\\w.+?)<";

    protected static final Pattern ID_PATTERN = Pattern.compile(DEN_ID, Pattern.DOTALL);
    protected static final Pattern NAME_PATTERN = Pattern.compile(DEN_NAME,Pattern.DOTALL);

    protected final String unitDesc;
    protected final int unitId;
    protected final boolean rosterPending;

    public Unit(String unitDesc, int unitId, boolean rosterPending) {
        this.unitDesc = unitDesc;
        this.unitId = unitId;
        this.rosterPending = rosterPending;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public int getUnitId() {
        return unitId;
    }

    public boolean getRosterPending() {
        return rosterPending;
    }

    public static void getDens(int unitId, DenHandler handler) throws Exception {
        logger.debug("getting dens for unit {}", unitId);
        ContentLoader.loadContent(URLFactory.getUnit(unitId), new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue != null) {
                    Matcher idMatcher = ID_PATTERN.matcher((String) newValue);
                    ArrayList<Den> denList = new ArrayList<Den>();

                    // FIXME This code relies on identifying the den ids and names from two separate REGEX
                    while (idMatcher.find()) {
                        int denId = Integer.parseInt(idMatcher.group("denId"));
                        denList.add(new Den("", denId));
                    }

                    int i = 0;
                    Matcher nameMatcher = NAME_PATTERN.matcher((String) newValue);
                    while (nameMatcher.find()) {
                        String denName = nameMatcher.group("denName").trim();
                        denList.get(i).setName(denName);
                        i++;
                    }

                    handler.processDenList(denList);
                }
            }
        });
    }

}
