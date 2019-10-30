package ru.privetdruk.rsbankupdateinstaller.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class HotFixTest {
    @Test
    public void testConstructor() {
        HotFix hotFix = new HotFix(null);
        System.out.println(hotFix.getNumber());
    }
}