package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.pawprints.model.Den;
import com.otabi.pawprints.model.ProgramAdventure;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Stephen on 11/17/2015.
 */
public class AdventureLoadService extends Service<Void> {
    protected ProgramAdventure adventure;
    protected Den den;
    protected BorderPane borderPane;

    public void setDen(Den den) {
        this.den = den;
    }

    public void setAdventure(ProgramAdventure adventure) {
        this.adventure = adventure;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    @Override
    protected Task createTask() {
        return new AdventureLoadTask(den, adventure, borderPane);
    }

    private class AdventureLoadTask extends Task<Void> {
        final protected BorderPane borderPane;
        final protected Den den;
        final protected ProgramAdventure adventure;

        AdventureLoadTask(Den den, ProgramAdventure adventureId, BorderPane borderPane) {
            this.adventure = adventureId;
            this.den = den;
            this.borderPane = borderPane;
        }

        @Override
        protected Void call() throws Exception {
            if (this.borderPane == null || this.den == null || this.adventure == null) {
                throw new IllegalArgumentException("Settings required to load adventure are null");
            }


            return null;
        }

        @Override
        protected void failed() {
            super.failed();
            Throwable e = this.getException();
            e.printStackTrace();
        }

        @Override
        protected void succeeded() {
            super.succeeded();


            FXMLLoader loader = new FXMLLoader(PawPrints.class.getResource("view/AdventurePane.fxml"));
            AdventurePaneController controller = new AdventurePaneController();
            loader.setController(controller);
            AnchorPane pane = null;
            try {
                pane = (AnchorPane) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //controller.bind(adventure, den);
            borderPane.setCenter(pane);
            reset();

        }
    }
}
