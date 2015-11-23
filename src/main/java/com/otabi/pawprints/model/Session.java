package com.otabi.pawprints.model;

import com.otabi.pawprints.PawPrints;
import com.otabi.scoutbook.*;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ObjDoubleConsumer;
import java.util.regex.Matcher;

/**
 * Created by Stephen on 11/21/2015.
 */
public class Session implements UnitHandler {
    final static Logger logger = LoggerFactory.getLogger(Session.class);
    public static Map<String, Session> sessions = new HashMap<String, Session>();

    protected String email;
    protected String password;
    protected ListProperty<Unit> unitListProperty = new SimpleListProperty<Unit>(FXCollections.observableArrayList());

    public Session(PawPrints pawPrints, String email, String password) throws Exception {
        this.email = email;
        this.password = password;

        try {
            Authentication.setEmail(email);
            Authentication.setPassword(password);
        } catch (Exception e) {
            logger.error("Error setting username and password.", e);
        }

        try {
            com.otabi.scoutbook.Session.getInstance();
        } catch (Exception e) {
            logger.info("Error while logging in.", e);
            throw new Exception(e);
        }

        sessions.put(email, this);

        logger.debug("loading units");
        Dashboard.getUnits(this);
        unitListProperty.addListener(new ListChangeListener<Unit>() {
            @Override
            public void onChanged(Change<? extends Unit> c) {
                logger.debug("unit list change listener {}", unitListProperty.size());
            }
        });
    }

    @Override
    public void processUnitList(ArrayList<com.otabi.scoutbook.Unit> scoutbookUnitList) {
        for (com.otabi.scoutbook.Unit scoutbookUnit : scoutbookUnitList) {
            Unit aUnit = new Unit(scoutbookUnit.getUnitDesc(), scoutbookUnit.getUnitId());
            logger.debug("Unit {} added.", aUnit.getUnitName());
            unitListProperty.add(aUnit);
        }
    }

    public ObservableList<Unit> getUnitListProperty() {
        return unitListProperty.get();
    }

    public ListProperty<Unit> unitListPropertyProperty() {
        return unitListProperty;
    }
}
