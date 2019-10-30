package ru.privetdruk.rsbankupdateinstaller.parser.impl;

import org.junit.Before;
import org.junit.Test;
import ru.privetdruk.rsbankupdateinstaller.model.GeneralSettings;
import ru.privetdruk.rsbankupdateinstaller.parser.Parser;

import java.io.File;

public class JaxbParserTest {
    private Parser parser;
    private File file;

    @Before
    public void setUp() {
        parser = new JaxbParser();
        file = new File("settings.xml");
    }

    @Test
    public void getObject() throws Exception {
        GeneralSettings generalSettings = (GeneralSettings) parser.getObject(file, GeneralSettings.class);
        System.out.println(generalSettings);
    }

    @Test
    public void saveObject() throws Exception {
        GeneralSettings generalSettings = new GeneralSettings();
        generalSettings.setDescription("(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = Sup-OraData1.bryansk.softlab.ru)(PORT = 1522)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = db122c1)))");
        generalSettings.setUser("BMW_20190803");
        generalSettings.setPassword("BMW_20190803");
        generalSettings.setRsBankFolderPath("D:\\RSBankV6\\");
        generalSettings.setHotFixFolderPath("D:\\HF\\");
        generalSettings.setBackupFolderPath("D:\\Backup\\");
        parser.saveObject(file, generalSettings);
    }

}