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

    public static Status createStatus(String status) {
        switch (status) {
            case "scheduled": return Status.SCHEDULED;
            case "in progress": return Status.IN_PROGRESS;
            case "done" : return Status.DONE;
            default: return Status.SCHEDULED;
        }
    }

}