package ru.privetdruk.rsbankupdateinstaller.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.privetdruk.rsbankupdateinstaller.Application;
import ru.privetdruk.rsbankupdateinstaller.model.GeneralSettings;
import ru.privetdruk.rsbankupdateinstaller.model.HotFix;
import ru.privetdruk.rsbankupdateinstaller.parser.Parser;
import ru.privetdruk.rsbankupdateinstaller.parser.impl.JaxbParser;
import ru.privetdruk.rsbankupdateinstaller.util.TextAreaAppender;

public class MainSceneController {
    @FXML
    private TableView<HotFix> hotFixesTable;
    @FXML
    private TableColumn<HotFix, Integer> numberColumn;
    @FXML
    private TableColumn<HotFix, String> statusColumn;
    @FXML
    private TableColumn<HotFix, String> pathColumn;

    @FXML
    private Label urlLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label rsBankFolderPathLabel;
    @FXML
    private Label hotFixFolderPathLabel;
    @FXML
    private Label backupFolderPathLabel;

    @FXML
    private TextArea logText;

    private Application application;

    private Parser parserGeneralSettings;

    private GeneralSettings generalSettings;

    public GeneralSettings getGeneralSettings() {
        return generalSettings;
    }

    @FXML
    private void initialize() {
        TextAreaAppender.textArea = logText;

        parserGeneralSettings = new JaxbParser();
        generalSettings = GeneralSettings.read();


        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty().asString());
        pathColumn.setCellValueFactory(cellData -> cellData.getValue().pathProperty());

        settingsFilling();

    }

    public void fillingListHotFixes(Application application) {
        this.application = application;
        hotFixesTable.setItems(application.getHotFixes());
    }

    public void settingsFilling() {
        urlLabel.setText(generalSettings.getDescription());
        userLabel.setText(generalSettings.getUser());
        passwordLabel.setText(generalSettings.getPassword());
        rsBankFolderPathLabel.setText(generalSettings.getRsBankFolderPath());
        hotFixFolderPathLabel.setText(generalSettings.getHotFixFolderPath());
        backupFolderPathLabel.setText(generalSettings.getBackupFolderPath());
    }

    @FXML
    private void handleTest() {
        Application.LOGGER.info("TEST");
    }
}
