package ru.privetdruk.rsbankupdateinstaller.controller;

import javafx.fxml.FXML;
import ru.privetdruk.rsbankupdateinstaller.Application;
import ru.privetdruk.rsbankupdateinstaller.model.GeneralSettings;
import ru.privetdruk.rsbankupdateinstaller.parser.impl.JaxbParser;

import java.io.File;

public class RootLayoutController {
    private Application application;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleSettings() throws Exception {
        boolean saveClicked = application.showSettingsEditDialog(application.getMainSceneController().getGeneralSettings());
        if (saveClicked) {
            application.getMainSceneController().settingsFilling();
        }
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
