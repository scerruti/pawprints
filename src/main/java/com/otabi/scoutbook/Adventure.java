package com.otabi.scoutbook;

import com.otabi.pawprints.model.ContentLoader;
import com.otabi.pawprints.model.Rank;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/14/2015.
 */
public class Adventure {
    final static Logger logger = LoggerFactory.getLogger(Den.class);
    protected static final String ADVENTURE_PATTERN =
            "id=\"requirementID\\d+?\" href=\"adventurerequirement\\.asp\\?RankID=(\\d+)&ScoutUserID=(\\d+)" +
                    "&AdventureID=(\\d+)&AdventureVersionID=(\\d+)&AdventureRequirementID=(\\d+)" +
                    ".*?icons/checkbox([a-zA-Z]+?)\\d+\\.png";

    protected static final Pattern PATTERN = Pattern.compile(ADVENTURE_PATTERN, Pattern.DOTALL);

    public void getRequirements(int scoutID, Rank rank, int adventure, final RequirementHandler handler) throws Exception {
        logger.debug("getting requirements for scout {} adventure {}", scoutID, adventure);
        ContentLoader.loadContent(URLFactory.getAdventure(scoutID, rank, adventure), new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue != null) {

                    HashMap<Integer, Requirement> requirementHashMap = new HashMap<Integer, Requirement>();

                    Matcher matcher = PATTERN.matcher(newValue);
                    while (matcher.find()) {
                        requirementHashMap.put(Integer.parseInt(matcher.group(5)),
                                new Requirement(Integer.parseInt(matcher.group(5)),
                                        Integer.parseInt(matcher.group(3)),
                                        RequirementStatus.fromCheckBoxName(matcher.group(6))));
                    }

                    handler.processRequirementMap(requirementHashMap);
                }
            }
        });
    }
}
