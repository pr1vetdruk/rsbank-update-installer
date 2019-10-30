package ru.privetdruk.rsbankupdateinstaller.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GeneralSettings")
@XmlType(propOrder = {"url", "user", "password", "rsBankFolderPath", "hotFixFolderPath", "backupFolderPath"})
public class GeneralSettings {
    private String url;
    private String user;
    private String password;
    private String rsBankFolderPath;
    private String hotFixFolderPath;
    private String backupFolderPath;
    private String host;

    public GeneralSettings() {
        host = url;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
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

    public static GeneralSettings createEmptyObject() {
        GeneralSettings emptyObject = new GeneralSettings();
        emptyObject.url = "";
        emptyObject.user = "";
        emptyObject.password = "";
        emptyObject.rsBankFolderPath = "";
        emptyObject.hotFixFolderPath = "";
        emptyObject.backupFolderPath = "";

        return emptyObject;
    }

    @Override
    public String toString() {
        return "GeneralSettings{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", rsBankFolderPath='" + rsBankFolderPath + '\'' +
                ", hotFixFolderPath='" + hotFixFolderPath + '\'' +
                ", backupFolderPath='" + backupFolderPath + '\'' +
                '}';
    }
}
