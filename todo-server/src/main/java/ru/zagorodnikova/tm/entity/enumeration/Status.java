package ru.zagorodnikova.tm.entity.enumeration;
import org.jetbrains.annotations.NotNull;

public enum Status {

    SCHEDULED("scheduled"),
    IN_PROGRESS("in progress"),
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