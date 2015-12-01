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
                            new ProgramRequirement[]{
                                    new ProgramRequirement(7, "1a"),
                                    new ProgramRequirement(8, "1b"),
                                    new ProgramRequirement(9, "1c"),
                                    new ProgramRequirement(10, "2"),
                                    new ProgramRequirement(11, "3"),
                                    new ProgramRequirement(12, "4"),
                                    new ProgramRequirement(13, "5")}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(3), new SimpleStringProperty("My Family's Duty to God"),
                            new int[]{15, 16, 17, 18}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(4), new SimpleStringProperty("Team Tiger"),
                            new int[]{19, 20, 21, 22, 23}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(5), new SimpleStringProperty("Tiger Bites"),
                            new int[]{24, 25, 26, 27, 28, 29}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(6), new SimpleStringProperty("Tigers in the Wild"),
                            new ProgramRequirement[]{
                                    new ProgramRequirement(30, "1"),
                                    new ProgramRequirement(31, "2"),
                                    new ProgramRequirement(33, "3a"),
                                    new ProgramRequirement(34, "3b"),
                                    new ProgramRequirement(35, "3c"),
                                    new ProgramRequirement(36, "4"),
                                    new ProgramRequirement(37, "5"),
                                    new ProgramRequirement(38, "6"),
                                    new ProgramRequirement(39, "7")}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(7), new SimpleStringProperty("Curiosity, Intrigue and Magical Mysteries"),
                            new int[]{40, 41, 42, 43, 44, 45, 46, 47}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(8), new SimpleStringProperty("Earning Your Stripes"),
                            new int[]{48, 49, 50, 51, 52, 53}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(9), new SimpleStringProperty("Family Stories"),
                            new int[]{54, 55, 56, 57, 58, 59, 60, 61}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(10), new SimpleStringProperty("Floats and Boats"),
                            new int[]{62, 63, 64, 65, 66, 67, 68}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(11), new SimpleStringProperty("Good Knights"),
                            new ProgramRequirement[]{
                                    new ProgramRequirement(70, "1a"),
                                    new ProgramRequirement(71, "1b"),
                                    new ProgramRequirement(72, "2"),
                                    new ProgramRequirement(73, "3"),
                                    new ProgramRequirement(74, "4"),
                                    new ProgramRequirement(75, "5")}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(12), new SimpleStringProperty("Rolling Tigers"),
                            new int[]{76, 77, 78, 79, 80, 81, 82, 83, 84}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(16), new SimpleStringProperty("Safe and Smart"),
                            new ProgramRequirement[]{
                                    new ProgramRequirement(110, "1a"),
                                    new ProgramRequirement(111, "1b"),
                                    new ProgramRequirement(112, "2a"),
                                    new ProgramRequirement(114, "2b"),
                                    new ProgramRequirement(115, "3"),
                                    new ProgramRequirement(116, "4"),
                                    new ProgramRequirement(117, "5"),
                                    new ProgramRequirement(118, "6"),
                                    new ProgramRequirement(119, "7")}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(13), new SimpleStringProperty("Sky Is the Limit"),
                            new int[]{85, 86, 87, 88, 89, 90, 91, 92}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(14), new SimpleStringProperty("Stories in Shapes"),
                            new ProgramRequirement[]{
                                    new ProgramRequirement(94, "1a"),
                                    new ProgramRequirement(95, "1b"),
                                    new ProgramRequirement(97, "2a"),
                                    new ProgramRequirement(98, "2b")}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(17), new SimpleStringProperty("Tiger Tag"),
                            new ProgramRequirement[]{
                                    new ProgramRequirement(120, "1"),
                                    new ProgramRequirement(122, "2a"),
                                    new ProgramRequirement(123, "2b"),
                                    new ProgramRequirement(124, "2c"),
                                    new ProgramRequirement(125, "3"),
                                    new ProgramRequirement(126, "4")}),

                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(18), new SimpleStringProperty("Tiger Tales"),
                            new int[]{127, 128, 129, 130, 131, 132, 133}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(19), new SimpleStringProperty("Tiger Theater"),
                            new int[]{134, 135, 136, 137, 138}),
                    new ProgramAdventure(Rank.TIGER, new SimpleIntegerProperty(15), new SimpleStringProperty("Tiger-iffic!"),
                            new ProgramRequirement[]{
                                    new ProgramRequirement(100, "1"),
                                    new ProgramRequirement(101, "2"),
                                    new ProgramRequirement(102, "3"),
                                    new ProgramRequirement(104, "4a"),
                                    new ProgramRequirement(105, "4b"),
                                    new ProgramRequirement(106, "4c"),
                                    new ProgramRequirement(107, "5"),
                                    new ProgramRequirement(108, "6")})));

        protected static ArrayList<ProgramAdventure> WOLF_ADVENTURES = new ArrayList<ProgramAdventure>(
                Arrays.asList(
                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(20), new SimpleStringProperty("Call of the Wild"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(139, "1"),
                                        new ProgramRequirement(140, "2"),
                                        new ProgramRequirement(141, "3"),
                                        new ProgramRequirement(142, "4"),
                                        new ProgramRequirement(144, "5a"),
                                        new ProgramRequirement(145, "5b"),
                                        new ProgramRequirement(146, "5c"),
                                        new ProgramRequirement(147, "6"),
                                        new ProgramRequirement(149, "7a"),
                                        new ProgramRequirement(150, "7b"),
                                        new ProgramRequirement(151, "7c")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(21), new SimpleStringProperty("Council Fire"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(783, "1"),
                                        new ProgramRequirement(152, "2"),
                                        new ProgramRequirement(154, "3a"),
                                        new ProgramRequirement(156, "3b"),
                                        new ProgramRequirement(158, "4a"),
                                        new ProgramRequirement(159, "4b"),
                                        new ProgramRequirement(160, "5"),
                                        new ProgramRequirement(162, "6a"),
                                        new ProgramRequirement(163, "6b"),
                                        new ProgramRequirement(164, "6c")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(22), new SimpleStringProperty("Duty to God Footsteps"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(166, "1a"),
                                        new ProgramRequirement(167, "1b"),
                                        new ProgramRequirement(169, "2a"),
                                        new ProgramRequirement(170, "2b"),
                                        new ProgramRequirement(171, "2c"),
                                        new ProgramRequirement(172, "2d")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(23), new SimpleStringProperty("Howling at the Moon"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(173, "1"),
                                        new ProgramRequirement(174, "2"),
                                        new ProgramRequirement(175, "3"),
                                        new ProgramRequirement(176, "4")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(24), new SimpleStringProperty("Paws on the Path"),
                                new int[]{177, 178, 179, 180, 181, 182, 183, 184}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(25), new SimpleStringProperty("Running with the Pack"),
                                new int[]{185, 186, 187, 188, 189, 190}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(26), new SimpleStringProperty("Adventures in Coins"),
                                new int[]{191, 192, 193, 194, 195, 196, 197}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(27), new SimpleStringProperty("Air of the Wolf"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(199, "1a"),
                                        new ProgramRequirement(200, "1b"),
                                        new ProgramRequirement(202, "1c i"),
                                        new ProgramRequirement(203, "1c ii"),
                                        new ProgramRequirement(204, "1c iii"),
                                        new ProgramRequirement(205, "1c iv"),
                                        new ProgramRequirement(207, "2a"),
                                        new ProgramRequirement(208, "2b"),
                                        new ProgramRequirement(209, "2c"),
                                        new ProgramRequirement(211, "3a"),
                                        new ProgramRequirement(212, "3b"),
                                        new ProgramRequirement(213, "4")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(28), new SimpleStringProperty("Code of the Wolf"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(215, "1a"),
                                        new ProgramRequirement(216, "1b"),
                                        new ProgramRequirement(218, "1c 1"),
                                        new ProgramRequirement(219, "1c 2"),
                                        new ProgramRequirement(220, "1c 3"),
                                        new ProgramRequirement(221, "1c 4"),
                                        new ProgramRequirement(222, "1c 5"),
                                        new ProgramRequirement(223, "1d"),
                                        new ProgramRequirement(224, "1e"),
                                        new ProgramRequirement(226, "2a"),
                                        new ProgramRequirement(227, "2b"),
                                        new ProgramRequirement(228, "2c"),
                                        new ProgramRequirement(231, "3a i"),
                                        new ProgramRequirement(232, "3a ii"),
                                        new ProgramRequirement(233, "3a iii"),
                                        new ProgramRequirement(234, "3a iv"),
                                        new ProgramRequirement(235, "3a v"),
                                        new ProgramRequirement(236, "3b"),
                                        new ProgramRequirement(237, "3c"),
                                        new ProgramRequirement(239, "4a"),
                                        new ProgramRequirement(240, "4b"),
                                        new ProgramRequirement(241, "4c")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(29), new SimpleStringProperty("Collections and Hobbies"),
                                new int[]{242, 243, 244, 245, 246, 247}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(30), new SimpleStringProperty("Cubs Who Care"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(248, "1"),
                                        new ProgramRequirement(250, "2a"),
                                        new ProgramRequirement(251, "2b"),
                                        new ProgramRequirement(252, "2c"),
                                        new ProgramRequirement(254, "2d i"),
                                        new ProgramRequirement(255, "2c ii"),
                                        new ProgramRequirement(256, "2c iii"),
                                        new ProgramRequirement(257, "2c iV"),
                                        new ProgramRequirement(258, "2c v"),
                                        new ProgramRequirement(259, "2c vi"),
                                        new ProgramRequirement(260, "e"),
                                        new ProgramRequirement(261, "f"),
                                        new ProgramRequirement(262, "g"),
                                        new ProgramRequirement(263, "h")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(31), new SimpleStringProperty("Digging in the Past"),
                                new int[]{264, 265, 266, 267, 268, 269}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(32), new SimpleStringProperty("Finding Your Way"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(271, "1a"),
                                        new ProgramRequirement(272, "1b"),
                                        new ProgramRequirement(273, "2"),
                                        new ProgramRequirement(275, "3a"),
                                        new ProgramRequirement(276, "3b"),
                                        new ProgramRequirement(277, "4"),
                                        new ProgramRequirement(278, "5")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(33), new SimpleStringProperty("Germs Alive!"),
                                new int[]{279, 280, 281, 282, 283, 284}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(34), new SimpleStringProperty("Grow Something"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(285, "1"),
                                        new ProgramRequirement(286, "2"),
                                        new ProgramRequirement(287, "3"),
                                        new ProgramRequirement(288, "4"),
                                        new ProgramRequirement(290, "5a"),
                                        new ProgramRequirement(291, "5b")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(35), new SimpleStringProperty("Hometown Heroes"),
                                new int[]{292, 293, 294, 295, 296, 297}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(36), new SimpleStringProperty("Motor Away"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(299, "1a"),
                                        new ProgramRequirement(300, "1b"),
                                        new ProgramRequirement(301, "2"),
                                        new ProgramRequirement(302, "3")}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(37), new SimpleStringProperty("Paws of Skill"),
                                new int[]{303, 304, 305, 306, 307, 308, 309}),

                        new ProgramAdventure(Rank.WOLF, new SimpleIntegerProperty(38), new SimpleStringProperty("Spirit of the Water"),
                                new int[]{310, 311, 312, 313, 314, 315})));

        protected static ArrayList<ProgramAdventure> BEAR_ADVENTURES = new ArrayList<ProgramAdventure>(
                Arrays.asList(
                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(39), new SimpleStringProperty("Bear Claws"),
                                new int[]{316, 317, 318}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(40), new SimpleStringProperty("Bear Necessities"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(319, "1"),
                                        new ProgramRequirement(320, "2"),
                                        new ProgramRequirement(321, "3"),
                                        new ProgramRequirement(324, "4"),
                                        new ProgramRequirement(326, "5"),
                                        new ProgramRequirement(328, "6"),
                                        new ProgramRequirement(330, "7"),
                                        new ProgramRequirement(332, "8"),
                                        new ProgramRequirement(333, "9")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(41), new SimpleStringProperty("Fellowship and Duty to God"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(323, "1"),
                                        new ProgramRequirement(327, "2a"),
                                        new ProgramRequirement(785, "2b"),
                                        new ProgramRequirement(331, "2c"),
                                        new ProgramRequirement(334, "2d")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(42), new SimpleStringProperty("Fur, Feathers and Ferns"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(336, "1"),
                                        new ProgramRequirement(337, "2"),
                                        new ProgramRequirement(338, "3"),
                                        new ProgramRequirement(340, "4"),
                                        new ProgramRequirement(342, "5"),
                                        new ProgramRequirement(345, "6"),
                                        new ProgramRequirement(347, "7")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(43), new SimpleStringProperty("Grin and Bear It"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(351, "1"),
                                        new ProgramRequirement(353, "2"),
                                        new ProgramRequirement(355, "3"),
                                        new ProgramRequirement(356, "4"),
                                        new ProgramRequirement(358, "5")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(44), new SimpleStringProperty("Paws for Action"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(339, "1a"),
                                        new ProgramRequirement(341, "1b"),
                                        new ProgramRequirement(343, "1c"),
                                        new ProgramRequirement(346, "2a"),
                                        new ProgramRequirement(349, "2b i"),
                                        new ProgramRequirement(350, "2b ii"),
                                        new ProgramRequirement(352, "2b iii"),
                                        new ProgramRequirement(354, "2b iv"),
                                        new ProgramRequirement(357, "2b v"),
                                        new ProgramRequirement(360, "3a"),
                                        new ProgramRequirement(363, "3b")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(45), new SimpleStringProperty("Baloo the Builder"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(361, "1"),
                                        new ProgramRequirement(786, "2"),
                                        new ProgramRequirement(364, "3"),
                                        new ProgramRequirement(365, "4")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(46), new SimpleStringProperty("A Bear Goes Fishing"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(366, "1"),
                                        new ProgramRequirement(368, "2"),
                                        new ProgramRequirement(370, "3"),
                                        new ProgramRequirement(372, "4")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(47), new SimpleStringProperty("Bear Picnic Basket"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(369, "1a"),
                                        new ProgramRequirement(805, "1b"),
                                        new ProgramRequirement(373, "1c"),
                                        new ProgramRequirement(377, "2a"),
                                        new ProgramRequirement(381, "2b"),
                                        new ProgramRequirement(382, "3")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(48), new SimpleStringProperty("Beat of the Drum"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(375, "1"),
                                        new ProgramRequirement(376, "2"),
                                        new ProgramRequirement(378, "3"),
                                        new ProgramRequirement(379, "4"),
                                        new ProgramRequirement(380, "5"),
                                        new ProgramRequirement(383, "6"),
                                        new ProgramRequirement(384, "7"),
                                        new ProgramRequirement(385, "8")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(49), new SimpleStringProperty("Critter Care Adventure"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(386, "1"),
                                        new ProgramRequirement(387, "2"),
                                        new ProgramRequirement(388, "3"),
                                        new ProgramRequirement(389, "4"),
                                        new ProgramRequirement(390, "5"),
                                        new ProgramRequirement(391, "6"),
                                        new ProgramRequirement(392, "7")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(50), new SimpleStringProperty("Forensics"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(393, "1"),
                                        new ProgramRequirement(394, "2"),
                                        new ProgramRequirement(397, "3"),
                                        new ProgramRequirement(399, "4"),
                                        new ProgramRequirement(401, "5"),
                                        new ProgramRequirement(403, "6"),
                                        new ProgramRequirement(405, "7"),
                                        new ProgramRequirement(407, "8")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(51), new SimpleStringProperty("Make It Move"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(395, "1"),
                                        new ProgramRequirement(396, "2"),
                                        new ProgramRequirement(398, "3"),
                                        new ProgramRequirement(402, "4a"),
                                        new ProgramRequirement(404, "4b")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(52), new SimpleStringProperty("Marble Madness"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(406, "1"),
                                        new ProgramRequirement(408, "2"),
                                        new ProgramRequirement(416, "3"),
                                        new ProgramRequirement(417, "4"),
                                        new ProgramRequirement(419, "5"),
                                        new ProgramRequirement(421, "6"),
                                        new ProgramRequirement(423, "7"),
                                        new ProgramRequirement(425, "8")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(53), new SimpleStringProperty("Roaring Laughter"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(409, "1"),
                                        new ProgramRequirement(410, "2"),
                                        new ProgramRequirement(411, "3"),
                                        new ProgramRequirement(412, "4"),
                                        new ProgramRequirement(413, "5"),
                                        new ProgramRequirement(414, "6")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(54), new SimpleStringProperty("Robotics "),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(415, "1"),
                                        new ProgramRequirement(418, "2"),
                                        new ProgramRequirement(420, "3"),
                                        new ProgramRequirement(422, "4"),
                                        new ProgramRequirement(424, "5")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(55), new SimpleStringProperty("Salmon Run"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(427, "1"),
                                        new ProgramRequirement(429, "2"),
                                        new ProgramRequirement(432, "3"),
                                        new ProgramRequirement(434, "4"),
                                        new ProgramRequirement(435, "5"),
                                        new ProgramRequirement(436, "6"),
                                        new ProgramRequirement(438, "7"),
                                        new ProgramRequirement(439, "8"),
                                        new ProgramRequirement(440, "9")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(56), new SimpleStringProperty("Super Science"),
                                new ProgramRequirement[]{
                                        new ProgramRequirement(426, "1"),
                                        new ProgramRequirement(428, "2"),
                                        new ProgramRequirement(430, "3"),
                                        new ProgramRequirement(431, "4"),
                                        new ProgramRequirement(433, "5"),
                                        new ProgramRequirement(437, "6")}),

                        new ProgramAdventure(Rank.BEAR, new SimpleIntegerProperty(57), new SimpleStringProperty("A World of Sound"),
                                new int[]{441, 442, 443})));

        protected static final ProgramAdvancement  program = new ProgramAdvancement();


    static {
            program.setAdventureMapForRank(Rank.TIGER, TIGER_ADVENTURES);
            program.setAdventureMapForRank(Rank.WOLF, WOLF_ADVENTURES);
            program.setAdventureMapForRank(Rank.BEAR, BEAR_ADVENTURES);
    }

    public static Map<Integer, ProgramAdventure> getAdventureMapByRank(Rank rank) {
        return program.getAdventureMapByRank(rank);
    }

    public static List<ProgramRequirement> getRequirementListByRankAndAdventure(Rank rank, int adventureId) {
        return getAdventureMapByRank(rank).get(adventureId).getRequriementMap();
    }
}
