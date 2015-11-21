package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.scoutbook.ContentLoadTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Stephen on 11/17/2015.
 */
public class ScoutbookLoadingController extends Stage {
    public ScoutbookLoadingController() {
        FXMLLoader loader = new FXMLLoader(PawPrints.class.getResource("view/ScoutbookLoading.fxml"));
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setScene(scene);
        scene.getRoot().visibleProperty().bind(ContentLoadTask.loadingPropertyProperty());

    }
}
