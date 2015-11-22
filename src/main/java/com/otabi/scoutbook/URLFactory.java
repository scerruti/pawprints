package com.otabi.scoutbook;

import com.otabi.pawprints.model.Rank;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Stephen on 11/14/2015.
 */
public class URLFactory {
    private static final String HOMEPAGE = "https://www.scoutbook.com/mobile/";
    private static final String LOGIN = "https://www.scoutbook.com/mobile/login.asp";
    private static final String DASHBOARD = "https://www.scoutbook.com/mobile/dashboard/";
    private static final String DEN = "https://www.scoutbook.com/mobile/dashboard/admin/denpatrol.asp?UnitID=%d&DenID=%d";
    private static final String SCOUT = "https://www.scoutbook.com/mobile/dashboard/admin/account.asp?ScoutUserID=%d";
    private static final String ADVANCEMENT = "https://www.scoutbook.com/mobile/dashboard/admin/advancement/rank.asp?ScoutUserID=%d&RankID=%d";
    private static final String ADVENTURE = "https://www.scoutbook.com/mobile/dashboard/admin/advancement/adventure.asp?RankID=%d&AdventureID=%d&ScoutUserID=%d";

    public static URL getHomepage() throws MalformedURLException {
        return new URL(HOMEPAGE);
    }

    public static URL getLogin() throws MalformedURLException {
        return new URL(LOGIN);
    }

    public static URL getDashboard() throws MalformedURLException {
        return new URL(DASHBOARD);
    }

    public static URL getDen(int unit, int den) throws MalformedURLException {
        return new URL(String.format(DEN, unit, den));
    }

    public static URL getScout(int id) throws MalformedURLException {
        return new URL(String.format(SCOUT, id));
    }

    public static URL getAdvancement(int scoutID, Rank rank) throws MalformedURLException {
        return new URL(String.format(ADVANCEMENT, scoutID, rank.getScoutbookId()));
    }

    public static URL getAdventure(int scoutID, Rank rank, int adventureID) throws MalformedURLException {
        return new URL(String.format(ADVENTURE, rank.getScoutbookId(), adventureID, scoutID));
    }
}
