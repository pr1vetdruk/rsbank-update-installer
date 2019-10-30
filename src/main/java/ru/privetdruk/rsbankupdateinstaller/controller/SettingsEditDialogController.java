package ru.privetdruk.rsbankupdateinstaller.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.privetdruk.rsbankupdateinstaller.model.GeneralSettings;

import java.util.Collections;

public class SettingsEditDialogController {
    @FXML
    private TextField descriptionField;
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
        addValidListener(descriptionField);
        addValidListener(userField);
        addValidListener(passwordField);
        addValidListener(rsBankFolderPathField);
        addValidListener(hotFixFolderPathField);
        addValidListener(backupFolderPathField);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setGeneralSettings(GeneralSettings generalSettings) {
        this.generalSettings = generalSettings;
        descriptionField.setText(generalSettings.getDescription());
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

            generalSettings.setDescription(descriptionField.getText());
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

        if (isInvalidValueField(descriptionField))
            errorMessage += "Не заполнено поле DESCRIPTION\n";
        if (isInvalidValueField(userField))
            errorMessage += "Не заполнено поле USER\n";
        if (isInvalidValueField(passwordField))
            errorMessage += "Не заполнено поле PASSWORD\n";
        if (isInvalidValueField(rsBankFolderPathField))
            errorMessage += "Не заполнено поле RS-BANK\n";
        if (isInvalidValueField(hotFixFolderPathField))
            errorMessage += "Не заполнено поле HOTFIX\n";
        if (isInvalidValueField(backupFolderPathField))
            errorMessage += "Не заполнено поле BACKUP\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка сохранения");
            alert.setHeaderText("При сохранение параметров возникла ошибка");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void addValidListener(TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                isInvalidValueField(field);
            }
        });
    }

    private boolean isInvalidValueField(TextField field) {
        ObservableList<String> styleClass = field.getStyleClass();
        if (field.getText().trim().length() == 0) {
            if (!styleClass.contains("error")) {
                if (styleClass.contains("success"))
                    styleClass.removeAll(Collections.singleton("success"));
                styleClass.add("error");
            }

            return true;
        } else {
            styleClass.removeAll(Collections.singleton("error"));
            styleClass.add("success");
            return false;
        }
    }
}
