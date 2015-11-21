package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.pawprints.model.Den;
import com.otabi.pawprints.model.ProgramAdventure;
import com.otabi.pawprints.model.ProgramRequirement;
import com.otabi.pawprints.model.Rank;
import com.otabi.pawprints.model.Scout;
import com.otabi.scoutbook.RequirementStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 11/16/2015.
 */
public class AdventurePaneController implements Initializable,ListChangeListener<Scout> {
    protected static final int MIN_COLUMN_WIDTH = 25;
    final static Logger logger = LoggerFactory.getLogger(AdventurePaneController.class);

    @FXML
    protected BorderPane adventurePane;

    @FXML
    protected Label adventureName;

    @FXML
    protected ImageView adventureLoop;

    @FXML
    protected TableView adventureTable;

    @FXML
    protected TableColumn reqColumn;

    protected PawPrints pawPrints;
    protected boolean denInitialized = false;
    protected Den currentDen;
    protected Pane header;
    protected ArrayList<TableColumn> scoutColumnns;


    public AdventurePaneController() {
    }

    @FXML

    public void setMain(final PawPrints pawPrints) {

        this.pawPrints = pawPrints;

        pawPrints.getAdventureSelectionController().setAdventureChangeListener(new ChangeListener<ProgramAdventure>() {
            public void changed(ObservableValue<? extends ProgramAdventure> observableValue, ProgramAdventure oldValue, ProgramAdventure newValue) {
                updateAdventureTable(newValue);
            }
        });

        pawPrints.getAdventureSelectionController().setDenChangeListener(new ChangeListener<Den>() {
            public void changed(ObservableValue<? extends Den> observableValue, Den oldValue, Den newValue) {
                updateDen(oldValue, newValue);
            }
        });

        reqColumn.setCellValueFactory(new PropertyValueFactory<com.otabi.pawprints.model.Adventure, String>("requirementName"));
        updateDen(null, pawPrints.getAdventureSelectionController().getSelectedDen());
        updateAdventureTable(pawPrints.getAdventureSelectionController().getSelectedAdventure());

    }

    private void updateDen(Den oldValue, Den newValue) {

        if (oldValue != null && oldValue.getScoutList() != null) {
            oldValue.getScoutList().removeListener(this);
            for (TableColumn column : scoutColumnns) {
                adventureTable.getColumns().remove(column);
            }
        }

        if (newValue != null && newValue.getScoutList() != null) {
            newValue.getScoutList().addListener(this);
            addTableColumns(newValue.getScoutList());
        }
    }

    private void addTableColumns(ObservableList<Scout> scoutList) {
        if (scoutColumnns == null) {
            scoutColumnns = new ArrayList<TableColumn>(scoutList.size());
        }
        for (final Scout scout : scoutList) {
            addColumn(scout);
        }
    }

    private void addColumn(final Scout scout) {
        TableColumn column = new TableColumn();
        Group columnHeader = new Group();
        Label name = new Label(scout.getName());
        name.setTextAlignment(TextAlignment.LEFT);
        name.setRotate(-90);
        columnHeader.getChildren().add(name);
        columnHeader.maxWidth(Double.MAX_VALUE);
        column.setGraphic(columnHeader);
        scoutColumnns.add(column);

        column.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ProgramRequirement, Integer>, ObservableValue<RequirementStatus>>() {

                    public ObservableValue<RequirementStatus> call(TableColumn.CellDataFeatures<ProgramRequirement, Integer> programRequirement) {
                        // FIXME Rank Hardcoded
                        ObjectProperty<RequirementStatus> requirement = scout.getRequirementStatus(Rank.TIGER, programRequirement.getValue());
                        if (requirement != null) {
                            return requirement;
                        } else {
                            return new SimpleObjectProperty<RequirementStatus>(RequirementStatus.LOADING);
                        }
                    }
                });

        column.setCellFactory(new Callback<TableColumn<ProgramRequirement, Integer>, TableCell<ProgramRequirement, RequirementStatus>>() {
            public TableCell<ProgramRequirement, RequirementStatus> call(TableColumn<ProgramRequirement, Integer> programRequirementIntegerTableColumn) {
                final Map<RequirementStatus, Image> imageMap = new HashMap<RequirementStatus, Image>();

                for (RequirementStatus requirementStatus : RequirementStatus.values()) {
                    imageMap.put(requirementStatus, new Image(String.format("com/otabi/pawprints/view/resources/icons/%s.png", requirementStatus)));
                }

                TableCell<ProgramRequirement, RequirementStatus> cell = new TableCell<ProgramRequirement, RequirementStatus>() {

                    ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(RequirementStatus requirementStatus, boolean empty) {
                        if (requirementStatus == null || empty || imageMap.get(requirementStatus) == null) {
                            imageView.imageProperty().setValue(null);
                        } else {
                            imageView.imageProperty().setValue(imageMap.get(requirementStatus));
                        }
                        setGraphic(imageView);
                    }
                };
                return cell;
            }
        });
        adventureTable.getColumns().add(column);
    }

    private void updateAdventureTable(final ProgramAdventure adventure) {
        if (adventure != null) {
            adventurePane.setVisible(true);
            adventureName.textProperty().setValue(adventure.getName());
            adventureLoop.imageProperty().setValue(adventure.getLoop());

            adventureTable.setItems(adventure.getObservableRequirementList());
        } else {
            adventurePane.setVisible(false);

        }
    }

    public void onChanged(Change<? extends Scout> change) {
        while (change.next()) {
            if (change.wasUpdated()) {
                for (Scout scout : change.getList()) {
                    // FIXME What should be done here?
                }
            } else {
                for (Scout removeScout : change.getRemoved()) {
                    // FIXME Remove Scout Column
                }

                for (Scout addedScout : change.getAddedSubList()) {
                    addColumn(addedScout);
                }
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}