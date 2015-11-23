package com.otabi.pawprints.controller;

import com.otabi.pawprints.PawPrints;
import com.otabi.pawprints.model.Session;
import com.otabi.scoutbook.Authentication;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginDialog extends Stage implements Initializable {
    final static Logger logger = LoggerFactory.getLogger(LoginDialog.class);

    @FXML
    private Text actiontarget;
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button loginButton;
    private PawPrints pawPrints;

    public LoginDialog(Parent parent) {
        logger.info("Loading LoginDialog.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/otabi/pawprints/view/LoginDialog/LoginDialog.fxml"));
        fxmlLoader.setController(this);

        try {
            setScene(new Scene((Parent) fxmlLoader.load()));
            bindUIandService();
        } catch (IOException e) {
            logger.error("Error loading LoginDialog.", e);
        }
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        loginService.start();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailInput.setText(Authentication.getEmail());
        passwordInput.setText(Authentication.getPassword());
    }

    private final Service loginService = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Session aSession = new Session(pawPrints, emailInput.getText(), passwordInput.getText());
                    pawPrints.getAdventureSelectionController().currentSessionProperty().setValue(aSession);
                    return null;
                }

                @Override
                protected void failed() {
                    super.failed();
                    Throwable e = this.getException();
                    logger.info("Error logging into Scoutbook.", e);
                    actiontarget.setText(String.format("%s\n%s", e.getMessage(), e.getCause().getMessage()));
                    reset();
                }

                @Override
                protected void succeeded() {
                    super.succeeded();
//                    getScene().getRoot().cursorProperty().unbind();
                    hide();
                }
            };
        }
    };


    private void bindUIandService() {
        getScene()
                .getRoot()
                .cursorProperty()
                .bind(
                        Bindings
                                .when(loginService.runningProperty())
                                .then(Cursor.WAIT)
                                .otherwise(Cursor.DEFAULT)
                );

        loginButton
                .disableProperty()
                .bind(
                        loginService.runningProperty()
                );
    }

    public void setMainApp(PawPrints pawPrints) {
        this.pawPrints = pawPrints;
    }
}