package com.otabi.scoutbook;

import com.otabi.pawprints.model.ContentLoader;
import com.otabi.pawprints.model.Rank;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/14/2015.
 */
public class Advancement {
    final static Logger logger = LoggerFactory.getLogger(Advancement.class);

    protected static final String ADVANCEMENT_PATTERN =
            "adventure\\.asp\\?" +
                    "(?:RankID=\\d+&AdventureID=(\\d+)&ScoutUserID=\\d+|" +
                    "ScoutUserID=\\d+&RankID=\\d+&AdventureID=(\\d+))" +
                    ".*?icons/checkbox([a-zA-Z]+?)\\d+\\.png";

    protected static final Pattern PATTERN = Pattern.compile(ADVANCEMENT_PATTERN, Pattern.DOTALL);

    public static void getAdventures(int scoutID, Rank rank, final AdventureHandler handler) throws Exception {
        logger.debug("getting adventures for {}", scoutID);
        ContentLoader.loadContent(URLFactory.getAdvancement(scoutID, rank), new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue != null) {
                    Map<Integer, RequirementStatus> adventureMap = new HashMap<Integer, RequirementStatus>();

                    Integer adventure;
                    Matcher matcher = PATTERN.matcher(newValue);
                    while (matcher.find()) {
                        if (matcher.group(1) != null) {
                            adventure = Integer.parseInt(matcher.group(1));
                        } else {
                            adventure = Integer.parseInt(matcher.group(2));
                        }
                        RequirementStatus status = RequirementStatus.fromCheckBoxName(matcher.group(3));
                        adventureMap.put(adventure, status);
                    }

                    handler.processAdventureMap(adventureMap);
                }
            }
        });
    }
}