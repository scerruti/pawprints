package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.pawprints.model.Den;
import com.otabi.pawprints.model.Program;
import com.otabi.pawprints.model.ProgramAdventure;
import com.otabi.pawprints.model.Rank;
import com.otabi.pawprints.model.RequirementStatus;
import com.otabi.pawprints.model.Session;
import com.otabi.pawprints.model.Unit;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 11/16/2015.
 */
public class AdventureSelectionController implements Initializable {
    final static Logger logger = LoggerFactory.getLogger(AdventureSelectionController.class);

    @FXML
    public PawPrint print;

    @FXML
    public ChoiceBox<RequirementStatus> printType;

    @FXML
    ChoiceBox<Unit> unitSelection;

    @FXML
    ChoiceBox<Den> denSelection;

    @FXML
    ChoiceBox<Rank> rankSelection;

    @FXML
    ListView<ProgramAdventure> adventureList;

    protected PawPrints main;

    protected ListProperty<Unit> unitListProperty = new SimpleListProperty<Unit>();
    protected ListProperty<Den> denListProperty = new SimpleListProperty<Den>(FXCollections.observableArrayList());
    protected ListProperty<Rank> rankListProperty = new SimpleListProperty<Rank>(FXCollections.observableArrayList());

    protected ObservableList<Den> denList = FXCollections.observableArrayList();
    protected ObservableList<Rank> rankList = FXCollections.observableArrayList();

    protected final ObjectProperty<Session> currentSession = new SimpleObjectProperty<>();


    @FXML
    public void initialize(URL url, ResourceBundle resource) {
        logger.info("Initializing AdventureSelectionController.");

        currentSession.addListener(new ChangeListener<Session>() {
            @Override
            public void changed(ObservableValue<? extends Session> observable, Session oldValue, Session newValue) {
                logger.debug("session changed");
                unitListProperty.bindBidirectional(getCurrentSession().unitListPropertyProperty());
                unitSelection.itemsProperty().bind(unitListProperty);
            }
        });
        unitListProperty.addListener(new ListChangeListener<Unit>() {
            @Override
            public void onChanged(Change<? extends Unit> c) {
                int currentSize = c.getList().size();
                int changeSize = 0;
                while (c.next()) {
                    if (c.wasAdded()) {
                        changeSize += c.getAddedSize();
                    } else if (c.wasRemoved()) {
                        changeSize -= c.getRemovedSize();
                    }
                }
                unitSelection.setDisable(currentSize == 0);
                if (currentSize > 0 && ((currentSize - changeSize) == 0)) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            unitSelection.getSelectionModel().selectFirst();
                        }
                    });
                }
            }
        });
        unitSelection.valueProperty().addListener(new ChangeListener<Unit>() {
            @Override
            public void changed(ObservableValue<? extends Unit> observable, Unit oldValue, Unit newValue) {
                if (newValue.getUnitName().startsWith("Pack")) {
                    setSelectedUnit(newValue);
                    denListProperty.bindBidirectional(newValue.getDenListProperty());
                    if (newValue.getDenListProperty().getValue().size() > 0) {
                        denSelection.getSelectionModel().selectFirst();
                        denSelection.setDisable(false);
                    }
                } else {
                    unitSelection.getSelectionModel().select(oldValue);
                }
            }
        });

        denSelection.itemsProperty().bind(denListProperty);
        denListProperty.addListener(new ListChangeListener<Den>() {
            @Override
            public void onChanged(Change<? extends Den> c) {
                logger.debug("list change {}", c.getList().size());
                int currentSize = c.getList().size();
                int changeSize = 0;
                while (c.next()) {
                    if (c.wasAdded()) {
                        changeSize += c.getAddedSize();
                    } else if (c.wasRemoved()) {
                        changeSize -= c.getRemovedSize();
                    }
                }
                denSelection.setDisable(currentSize == 0);
                if (currentSize > 0 && ((currentSize - changeSize) == 0)) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            denSelection.getSelectionModel().selectFirst();
                        }
                    });
                }
            }
        });
        denSelection.valueProperty().addListener(new ChangeListener<Den>() {
            @Override
            public void changed(ObservableValue<? extends Den> observable, Den oldValue, Den newValue) {
                rankSelection.valueProperty().setValue(newValue.getRank());
            }
        });
        rankSelection.itemsProperty().bind(rankListProperty);

        rankSelection.valueProperty().addListener(new ChangeListener<Rank>() {
            @Override
            public void changed(ObservableValue<? extends Rank> observable, Rank oldValue, Rank newValue) {
                adventureList.setItems(FXCollections.<ProgramAdventure>observableArrayList(
                        new ArrayList<ProgramAdventure>(Program.getAdventureMapByRank(newValue).values())));
                adventureList.getSelectionModel().clearSelection();
            }
        });

        adventureList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProgramAdventure>() {
            public void changed(ObservableValue<? extends ProgramAdventure> observableValue, ProgramAdventure oldValue, ProgramAdventure newValue) {
                if (newValue != null) {
                    getSelectedDen().loadAdventure(getSelectedAdventure());
                }
            }
        });

    }

    private void setSelectedUnit(Unit unit) {
        denListProperty.bindBidirectional(unit.getDenListProperty());
    }

    public void setMain(PawPrints main) {
        this.main = main;
    }

    public Den getSelectedDen() {
        return denSelection.getSelectionModel().getSelectedItem();
    }

    public ProgramAdventure getSelectedAdventure() {
        return adventureList.getSelectionModel().getSelectedItem();
    }

    public void setAdventureChangeListener(ChangeListener<ProgramAdventure> changeListener) {
        adventureList.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void setDenChangeListener(ChangeListener<Den> changeListener) {
        denSelection.valueProperty().addListener(changeListener);
    }

    public ChoiceBox<Unit> getUnitSelection() {
        return unitSelection;
    }

    public Session getCurrentSession() {
        return currentSession.get();
    }

    public ObjectProperty<Session> currentSessionProperty() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession.set(currentSession);
    }
}
