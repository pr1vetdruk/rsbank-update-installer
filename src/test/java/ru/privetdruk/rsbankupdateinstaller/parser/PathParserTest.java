package ru.privetdruk.rsbankupdateinstaller.parser;

import org.junit.Before;
import org.junit.Test;

public class PathParserTest {
    private String path;

    @Before
    public void setUp() {
        path = "D:\\HotFix\\123";
    }

    @Test
    public void getHotfixNumber() {
        try {
            Integer number = PathParser.getHotFixNumber(path);
            System.out.println("Number = " + number);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getHotfixNumberIsNull() {
        try {
            Integer number = PathParser.getHotFixNumber(path);
            System.out.println("Number = " + number);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}