package com.otabi.pawprints.controller;

/**
 * Created by Stephen on 11/23/2015.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.otabi.pawprints.model.Requirement;
import com.otabi.pawprints.model.RequirementStatus;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PawPrint extends StackPane implements Initializable {
    final static Logger logger = LoggerFactory.getLogger(PawPrint.class);
    final static String[] PAWPRINT_STYLE_CLASSES = RequirementStatus.getNames();
    final static HashMap<RequirementStatus, Color> STROKE = new HashMap<>(RequirementStatus.values().length);
    final static HashMap<RequirementStatus, Color> FILL = new HashMap<>(RequirementStatus.values().length);

    @FXML
    protected Group pawPrint;

    ObjectProperty<RequirementStatus> requirementStatusProperty = new SimpleObjectProperty<RequirementStatus>(RequirementStatus.INVALID);

    {
        STROKE.put(null, Color.TRANSPARENT);
        STROKE.put(RequirementStatus.APPROVED, Color.BLUE);
        STROKE.put(RequirementStatus.AWARDED, Color.GOLD);
        STROKE.put(RequirementStatus.COMPLETED, Color.GREEN);
        STROKE.put(RequirementStatus.INVALID, Color.GRAY);
        STROKE.put(RequirementStatus.LOADING, Color.GRAY);
        STROKE.put(RequirementStatus.NOT_STARTED, Color.BLACK);
        STROKE.put(RequirementStatus.STARTED, Color.GREEN);

        FILL.put(null, Color.TRANSPARENT);
        FILL.put(RequirementStatus.APPROVED, Color.BLUE);
        FILL.put(RequirementStatus.AWARDED, Color.GOLD);
        FILL.put(RequirementStatus.COMPLETED, Color.GREEN);
        FILL.put(RequirementStatus.INVALID, Color.GRAY);
        FILL.put(RequirementStatus.LOADING, Color.TRANSPARENT);
        FILL.put(RequirementStatus.NOT_STARTED, Color.TRANSPARENT);
        FILL.put(RequirementStatus.STARTED, Color.TRANSPARENT);
    }
    public PawPrint() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/otabi/pawprints/view/PawPrint/PawPrint.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resource) {
        logger.info("Initialize");
        requirementStatusProperty.addListener(new ChangeListener<RequirementStatus>() {
            @Override
            public void changed(ObservableValue<? extends RequirementStatus> observable, RequirementStatus oldValue, RequirementStatus newValue) {
                if (newValue == null) {
                    logger.info("PawPrint null");
                    pawPrint.setVisible(false);
                } else {
                    pawPrint.setVisible(true);
                    logger.info("PawPrint {} {}", newValue.name(), pawPrint.getStyleClass().toString());
//                    pawPrint.getStyleClass().add(newValue.name());
//                    ArrayList<String> otherStyles = new ArrayList<String>(Arrays.asList(PAWPRINT_STYLE_CLASSES));
//                    otherStyles.remove(newValue.name());
//                    pawPrint.getStyleClass().removeAll(otherStyles);
                    transition(oldValue, newValue);
                }
            }
        });
    }

    void transition(RequirementStatus oldValue, RequirementStatus newValue) {

        ParallelTransition parallelTransition = new ParallelTransition();
        for (Node child : pawPrint.getChildren()) {
            if (!(child instanceof  Shape)) continue;

            logger.error("{} {}", FILL.get(oldValue), FILL.get(newValue));
            FillTransition ft = new FillTransition(Duration.millis(3000), (Shape) child, FILL.get(oldValue), FILL.get(newValue));
            StrokeTransition st = new StrokeTransition(Duration.millis(500), (Shape) child, STROKE.get(oldValue), STROKE.get(newValue));
            parallelTransition.getChildren().add(ft);
            parallelTransition.getChildren().add(st);
        }
        parallelTransition.play();
    }
}
