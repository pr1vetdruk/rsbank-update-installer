package ru.privetdruk.rsbankupdateinstaller.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.privetdruk.rsbankupdateinstaller.model.GeneralSettings;

public class SettingsEditDialogController {
    @FXML
    private TextField urlField;
    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField rsBankFolderPathField;
    @FXML
    private TextField hotFixFolderPathField;
    @FXML
    private TextField backupFolderPathField;

    private Stage dialogStage;
    private GeneralSettings generalSettings;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setGeneralSettings(GeneralSettings generalSettings) {
        this.generalSettings = generalSettings;

        urlField.setText(generalSettings.getUrl());
        userField.setText(generalSettings.getUser());
        passwordField.setText(generalSettings.getPassword());
        rsBankFolderPathField.setText(generalSettings.getRsBankFolderPath());
        hotFixFolderPathField.setText(generalSettings.getHotFixFolderPath());
        backupFolderPathField.setText(generalSettings.getBackupFolderPath());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            generalSettings.setUrl(urlField.getText());
            generalSettings.setUser(userField.getText());
            generalSettings.setPassword(passwordField.getText());
            generalSettings.setRsBankFolderPath(rsBankFolderPathField.getText());
            generalSettings.setHotFixFolderPath(hotFixFolderPathField.getText());
            generalSettings.setBackupFolderPath(backupFolderPathField.getText());

            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (isTextFieldValid(urlField))
            errorMessage += "No valid urlField!\n";
        if (isTextFieldValid(userField))
            errorMessage += "No valid userField!\n";
        if (isTextFieldValid(passwordField))
            errorMessage += "No valid passwordField!\n";
        if (isTextFieldValid(rsBankFolderPathField))
            errorMessage += "No valid rsBankFolderPathField!\n";
        if (isTextFieldValid(hotFixFolderPathField))
            errorMessage += "No valid hotFixFolderPathField!\n";
        if (isTextFieldValid(backupFolderPathField))
            errorMessage += "No valid backupFolderPathField!\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private boolean isTextFieldValid(TextField field) {
        return field.getText() == null && field.getText().length() != 0;
    }
}
