package ru.privetdruk.rsbankupdateinstaller.model;

enum HotFixStatus {
    DEFINED("определен"),
    ERROR_INVALID_NUMBER_HF("ошибка [невалидный номер хф]"),
    ERROR("ошибка");

    private String status;

    HotFixStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
