package com.otabi.pawprints;/**
 * Created by Stephen on 11/15/2015.
 */

import com.otabi.pawprints.controller.AdventureLoadService;
import com.otabi.pawprints.controller.AdventurePaneController;
import com.otabi.pawprints.controller.AdventureSelectionController;
import com.otabi.pawprints.controller.LoginDialog;
import com.otabi.pawprints.controller.ScoutbookLoadingController;
import com.otabi.pawprints.model.ContentLoader;
import com.otabi.pawprints.model.ProgramAdvancement;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class PawPrints extends Application {

    private static Stage primaryStage;
    private SplitPane rootLayout;
    private GridPane adventureGrid;
    protected Scene scene = null;
    public static final ProgramAdvancement PROGRAM = new ProgramAdvancement();
    protected AdventureSelectionController adventureSelectionController;
    protected BorderPane borderPane = new BorderPane();
    AdventureLoadService adventureLoader = new AdventureLoadService();

    public AdventureSelectionController getAdventureSelectionController() {
        return adventureSelectionController;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryStage) {
        PawPrints.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        this.primaryStage.setTitle("Paw Prints");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = (SplitPane) loader.load();
            scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
        adventureLoader.setBorderPane(borderPane);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdventureSelection.fxml"));
        SplitPane pane = null;
        try {
            pane = (SplitPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.getItems().add(pane);
        rootLayout.getItems().add(borderPane);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                ContentLoader.cancelTimer();
                Platform.exit();
            }
        });

        adventureSelectionController = (AdventureSelectionController) loader.getController();
        adventureSelectionController.setMain(this);
        primaryStage.show();

        LoginDialog loginDialog = new LoginDialog(null);
        loginDialog.setMainApp(this);
        loginDialog.showAndWait();
        scene.getRoot().cursorProperty().setValue(Cursor.DEFAULT);

        //rootLayout.setBottom(progressPane);
        //denService.start();

        loader = new FXMLLoader(getClass().getResource("view/AdventurePane.fxml"));
        AdventurePaneController controller = new AdventurePaneController();
        loader.setController(controller);
        AnchorPane selectionPane = null;
        try {
            selectionPane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(selectionPane);
        controller.setMain(this);

        ScoutbookLoadingController loading = new ScoutbookLoadingController();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

