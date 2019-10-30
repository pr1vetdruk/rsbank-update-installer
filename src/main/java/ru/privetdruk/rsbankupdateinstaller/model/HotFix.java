package ru.privetdruk.rsbankupdateinstaller.model;

import javafx.beans.property.*;
import ru.privetdruk.rsbankupdateinstaller.exception.parser.PathParseException;
import ru.privetdruk.rsbankupdateinstaller.parser.PathParser;

public class HotFix {
    private StringProperty path;
    private IntegerProperty number;
    private ObjectProperty<HotFixStatus> status;

    public HotFix(String path) {
        this.path = new SimpleStringProperty(path);

        try {
            number = new SimpleIntegerProperty(PathParser.getHotFixNumber(path));
            status = new SimpleObjectProperty<HotFixStatus>(HotFixStatus.DEFINED);
        } catch (PathParseException ex) {
            number = new SimpleIntegerProperty(-1);
            status = new SimpleObjectProperty<HotFixStatus>(HotFixStatus.ERROR_INVALID_NUMBER_HF);
        }
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public HotFixStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<HotFixStatus> statusProperty() {
        return status;
    }

    public void setStatus(HotFixStatus status) {
        this.status.set(status);
    }
}
