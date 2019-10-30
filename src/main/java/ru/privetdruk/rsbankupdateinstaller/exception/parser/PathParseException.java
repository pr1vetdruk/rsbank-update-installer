package ru.privetdruk.rsbankupdateinstaller.exception.parser;

public class PathParseException extends Exception {
    public PathParseException(String message) {
        super(message);
    }

    public PathParseException() {
        super("Возникла ошибка при парсинге номера хот-фикса");
    }
}
