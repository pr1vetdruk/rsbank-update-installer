package ru.privetdruk.rsbankupdateinstaller.model;

import ru.privetdruk.rsbankupdateinstaller.Application;
import ru.privetdruk.rsbankupdateinstaller.parser.Parser;
import ru.privetdruk.rsbankupdateinstaller.parser.impl.JaxbParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;

@XmlRootElement(name = "GeneralSettings")
@XmlType(propOrder = {"description", "user", "password", "rsBankFolderPath", "hotFixFolderPath", "backupFolderPath"})
public class GeneralSettings {
    private String description;
    private String user;
    private String password;
    private String rsBankFolderPath;
    private String hotFixFolderPath;
    private String backupFolderPath;
    private String host;

    private static Parser parserGeneralSettings = new JaxbParser();

    public GeneralSettings() {

    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    @XmlElement
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRsBankFolderPath() {
        return rsBankFolderPath;
    }

    @XmlElement
    public void setRsBankFolderPath(String rsBankFolderPath) {
        this.rsBankFolderPath = rsBankFolderPath;
    }

    public String getHotFixFolderPath() {
        return hotFixFolderPath;
    }

    @XmlElement
    public void setHotFixFolderPath(String hotFixFolderPath) {
        this.hotFixFolderPath = hotFixFolderPath;
    }

    public String getBackupFolderPath() {
        return backupFolderPath;
    }

    @XmlElement
    public void setBackupFolderPath(String backupFolderPath) {
        this.backupFolderPath = backupFolderPath;
    }

    private static GeneralSettings createEmptyObject() {
        GeneralSettings emptyObject = new GeneralSettings();
        emptyObject.description = "";
        emptyObject.user = "";
        emptyObject.password = "";
        emptyObject.rsBankFolderPath = "";
        emptyObject.hotFixFolderPath = "";
        emptyObject.backupFolderPath = "";

        return emptyObject;
    }

    public static GeneralSettings read() {
        GeneralSettings generalSettings = GeneralSettings.createEmptyObject();
        String fileNameGeneralSettings = Application.CONFIG.getProperty("general.filename");
        try  {
            generalSettings = (GeneralSettings) parserGeneralSettings.getObject(new File(fileNameGeneralSettings), GeneralSettings.class);
            Application.LOGGER.info("Файл настроек " + fileNameGeneralSettings + " успешно загружен");
        } catch (Exception ex) {
            Application.LOGGER.error("Произошла ошибка при загрузке файла настроек " + fileNameGeneralSettings + " " + ex.getMessage());
            Application.LOGGER.info("Автоматическое создание файла настроек " + fileNameGeneralSettings);
            generalSettings.save();
        }
        return generalSettings;
    }

    public void save() {
        String fileNameGeneralSettings = Application.CONFIG.getProperty("general.filename");
        try {
            parserGeneralSettings.saveObject(new File(fileNameGeneralSettings), this);
            Application.LOGGER.info("Файл настроек " + fileNameGeneralSettings + " успешно сохранен");
        } catch (Exception ex) {
            Application.LOGGER.error("Произошла ошибка при создании файла настроек " + fileNameGeneralSettings + " " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "GeneralSettings{" +
                "url='" + description + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", rsBankFolderPath='" + rsBankFolderPath + '\'' +
                ", hotFixFolderPath='" + hotFixFolderPath + '\'' +
                ", backupFolderPath='" + backupFolderPath + '\'' +
                '}';
    }
}
