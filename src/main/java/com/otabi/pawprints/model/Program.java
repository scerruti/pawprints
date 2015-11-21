package com.otabi.pawprints.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 11/19/2015.
 */
public class Program {
    final static Logger logger = LoggerFactory.getLogger(Program.class);

    protected static ArrayList<ProgramAdventure> TIGER_ADVENTURES = new ArrayList<ProgramAdventure>(
            Arrays.asList(
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(1), new SimpleStringProperty("Backyard Jungle"),
                            new int[]{1, 2, 3, 4, 5}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(2), new SimpleStringProperty("Games Tigers Play"),
                            new int[]{7, 8, 9, 10, 11, 12, 13}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(3), new SimpleStringProperty("My Family's Duty to God"),
                            new int[]{15, 16, 17, 18}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(4), new SimpleStringProperty("Team Tiger"),
                            new int[]{19, 20, 21, 22, 23}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(5), new SimpleStringProperty("Tiger Bites"),
                            new int[]{24, 25, 26, 27, 28, 29}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(6), new SimpleStringProperty("Tigers in the Wild"),
                            new int[]{30, 31, 33, 34, 35, 36, 37, 38, 39}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(7), new SimpleStringProperty("Curiosity, Intrigue and Magical Mysteries"),
                            new int[]{40, 41, 42, 43, 44, 45, 46, 47}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(8), new SimpleStringProperty("Earning Your Stripes"),
                            new int[]{48, 49, 50, 51, 52, 53}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(9), new SimpleStringProperty("Family Stories"),
                            new int[]{54, 55, 56, 57, 58, 59, 60, 61}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(10), new SimpleStringProperty("Floats and Boats"),
                            new int[]{62, 63, 64, 65, 66, 67, 68}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(11), new SimpleStringProperty("Good Knights"),
                            new int[]{70, 71, 72, 73, 74, 75}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(12), new SimpleStringProperty("Rolling Tigers"),
                            new int[]{76, 77, 78, 79, 80, 81, 82, 83, 84}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(16), new SimpleStringProperty("Safe and Smart"),
                            new int[]{110, 111, 112, 114, 115, 116, 117, 118, 119}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(13), new SimpleStringProperty("Sky Is the Limit"),
                            new int[]{85, 86, 87, 88, 89, 90, 91, 92}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(14), new SimpleStringProperty("Stories in Shapes"),
                            new int[]{94, 95, 97, 98}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(17), new SimpleStringProperty("Tiger Tag"),
                            new int[]{120, 122, 123, 124, 125, 126}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(18), new SimpleStringProperty("Tiger Tales"),
                            new int[]{127, 128, 129, 130, 131, 132, 133}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(19), new SimpleStringProperty("Tiger Theater"),
                            new int[]{134, 135, 136, 137, 138}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(15), new SimpleStringProperty("Tiger-iffic!"),
                            new int[]{100, 101, 102, 104, 105, 106, 107, 108})));

    protected static final ProgramAdvancement  program = new ProgramAdvancement();


    static {
        program.setAdventureMapForRank(Rank.TIGER, TIGER_ADVENTURES);
    }

    public static Map<Integer, ProgramAdventure> getAdventureMapByRank(Rank rank) {
        return program.getAdventureMapByRank(rank);
    }

    public static List<ProgramRequirement> getRequirementListByRankAndAdventure(Rank rank, int adventureId) {
        return getAdventureMapByRank(rank).get(adventureId).getRequriementMap();
    }
}
