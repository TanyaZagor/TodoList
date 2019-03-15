package ru.zagorodnikova.tm.entity;

import org.jetbrains.annotations.NotNull;

public enum Status {

    SCHEDULED("scheduled"),
    INPROGRESS("in progress"),
    DONE("done");

    @NotNull private final String displayName;

    Status(@NotNull final String display) {
        this.displayName = display;
    }

    @NotNull
    @Override
    public String toString() {
        return this.displayName;
    }

}
