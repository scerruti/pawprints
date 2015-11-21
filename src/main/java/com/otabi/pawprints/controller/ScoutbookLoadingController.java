package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.scoutbook.ContentLoadTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Stephen on 11/17/2015.
 */
public class ScoutbookLoadingController extends Stage {
    final static Logger logger = LoggerFactory.getLogger(ScoutbookLoadingController.class);

    public ScoutbookLoadingController() {
        logger.info("Loading ScoutbookLoading.fxml");
        FXMLLoader loader = new FXMLLoader(PawPrints.class.getResource("/com/otabi/pawprints/view/ScoutbookLoading.fxml"));
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load());
        } catch (IOException e) {
            logger.error("Error loading ScoubookLoading.", e);
        }

        setScene(scene);
        scene.getRoot().visibleProperty().bind(ContentLoadTask.loadingPropertyProperty());
    }
}
