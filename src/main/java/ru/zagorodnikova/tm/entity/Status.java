package ru.zagorodnikova.tm.entity;

public enum Status {

    SCHEDULED("scheduled"),
    INPROGRESS("in progress"),
    DONE("done");

    private final String displayName;

    Status(final String display) {
        this.displayName = display;
    }


    @Override
    public String toString() {
        return this.displayName;
    }

}
