package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.pawprints.model.Den;
import com.otabi.pawprints.model.Program;
import com.otabi.pawprints.model.ProgramAdventure;
import com.otabi.pawprints.model.Rank;
import com.otabi.pawprints.model.Unit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
    ChoiceBox<Unit> unitSelection;

    @FXML
    ChoiceBox<Den> denSelection;

    @FXML
    ChoiceBox<Rank> rankSelection;

    @FXML
    ListView<ProgramAdventure> adventureList;

    protected PawPrints main;

    @FXML
    public void initialize(URL url, ResourceBundle resource) {
        logger.info("Initializing AdventureSelectionController.");

        Unit defaultUnit = new Unit("Pack 7146", 3706);
        unitSelection.itemsProperty().setValue(FXCollections.<Unit>observableArrayList(defaultUnit));
        unitSelection.setItems(FXCollections.<Unit>observableArrayList(defaultUnit));
        unitSelection.getSelectionModel().selectFirst();

        Den defaultDen = new Den(defaultUnit.getUnitId(), 25001, "Tiger Cub Den 3");
        denSelection.setItems(FXCollections.<Den>observableArrayList(defaultDen));
        denSelection.getSelectionModel().selectFirst();

        rankSelection.getItems().clear();
        rankSelection.setItems(FXCollections.<Rank>observableArrayList(Rank.values()));
        rankSelection.getSelectionModel().selectFirst();

        adventureList.setItems(FXCollections.<ProgramAdventure>observableArrayList(
                new ArrayList<ProgramAdventure>(Program.getAdventureMapByRank(rankSelection.getValue()).values())));
        adventureList.getSelectionModel().clearSelection();
        adventureList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProgramAdventure>() {
            public void changed(ObservableValue<? extends ProgramAdventure> observableValue, ProgramAdventure oldValue, ProgramAdventure newValue) {
                if (newValue != null) {
                    getSelectedDen().loadAdventure(getSelectedAdventure());
                }
            }
        });

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
}
