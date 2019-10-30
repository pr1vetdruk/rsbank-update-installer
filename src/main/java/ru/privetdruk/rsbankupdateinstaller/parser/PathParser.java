package ru.privetdruk.rsbankupdateinstaller.parser;

import ru.privetdruk.rsbankupdateinstaller.exception.parser.PathParseException;

public class PathParser {
    public static Integer getHotFixNumber(String path) throws PathParseException {
        try {
            return Integer.parseInt(path.substring(path.lastIndexOf('\\') + 1));
        } catch (Exception ex) {
            throw new PathParseException();
        }
    }
}
