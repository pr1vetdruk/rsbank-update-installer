package ru.privetdruk.rsbankupdateinstaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.privetdruk.rsbankupdateinstaller.controller.MainSceneController;
import ru.privetdruk.rsbankupdateinstaller.controller.RootLayoutController;
import ru.privetdruk.rsbankupdateinstaller.controller.SettingsEditDialogController;
import ru.privetdruk.rsbankupdateinstaller.model.GeneralSettings;
import ru.privetdruk.rsbankupdateinstaller.model.HotFix;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application extends javafx.application.Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<HotFix> hotFixes = FXCollections.observableArrayList();
    private MainSceneController mainSceneController;
    private SettingsEditDialogController settingsEditDialogController;

    public MainSceneController getMainSceneController() {
        return mainSceneController;
    }

    public SettingsEditDialogController getSettingsEditDialogController() {
        return settingsEditDialogController;
    }

    public static final Logger LOGGER;
    public static final Properties CONFIG;

    static {
        CONFIG = new Properties();
        try (InputStream file = Application.class.getResourceAsStream("/config.properties")) {
            Properties loggingProperties = new Properties();
            loggingProperties.load(Application.class.getResourceAsStream("/log4j2.properties"));
            PropertyConfigurator.configure(loggingProperties);
            CONFIG.load(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER = Logger.getLogger(Application.class);
    }

    public Application() throws Exception {

        /*hotFixes.add(new HotFix("D:\\HF\\1"));
        hotFixes.add(new HotFix("D:\\HF\\2"));
        hotFixes.add(new HotFix("D:\\HF\\3"));
        hotFixes.add(new HotFix("D:\\HF\\4"));
        hotFixes.add(new HotFix("D:\\HF\\5"));*/
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Запуск приложения");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(CONFIG.getProperty("application.title"));
        this.primaryStage.getIcons().add(new Image(CONFIG.getProperty("application.icon")));

        initRootLayout();
        showMainScene();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource(CONFIG.getProperty("rootLayout.fxml")));
            rootLayout = (BorderPane) loader.load();

            RootLayoutController controller = loader.getController();
            controller.setApplication(this);

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showMainScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource(CONFIG.getProperty("mainScene.fxml")));

            rootLayout.setCenter((AnchorPane) loader.load());
            mainSceneController = loader.getController();
            mainSceneController.fillingListHotFixes(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean showSettingsEditDialog(GeneralSettings generalSettings) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource(CONFIG.getProperty("settingsDialog.fxml")));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(CONFIG.getProperty("settingsDialog.title"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            dialogStage.setScene(scene);

            settingsEditDialogController = loader.getController();
            settingsEditDialogController.setDialogStage(dialogStage);
            settingsEditDialogController.setGeneralSettings(generalSettings);

            dialogStage.showAndWait();

            return settingsEditDialogController.isSaveClicked();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<HotFix> getHotFixes() {
        return hotFixes;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
