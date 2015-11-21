package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.pawprints.model.Den;
import com.otabi.pawprints.model.ProgramAdventure;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Stephen on 11/17/2015.
 */
public class AdventureLoadService extends Service<Void> {
    final static Logger logger = LoggerFactory.getLogger(AdventureLoadService.class);

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
                logger.error("Settings required to load adventure are null.");
                throw new IllegalArgumentException("Settings required to load adventure are null");
            }


            return null;
        }

        @Override
        protected void failed() {
            super.failed();
            Throwable e = this.getException();
            logger.error("Error loading adventure.", e);
        }

        @Override
        protected void succeeded() {
            super.succeeded();


            FXMLLoader loader = new FXMLLoader(PawPrints.class.getResource("/com/otabi/pawprints/view/AdventurePane.fxml"));
            AdventurePaneController controller = new AdventurePaneController();
            loader.setController(controller);
            AnchorPane pane = null;
            try {
                pane = (AnchorPane) loader.load();
                borderPane.setCenter(pane);
            } catch (IOException e) {
                logger.error("Error loading adventure pane.", e);
            } finally {
                reset();
            }
        }
    }
}
