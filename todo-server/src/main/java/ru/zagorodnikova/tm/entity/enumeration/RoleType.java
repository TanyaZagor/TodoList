package ru.zagorodnikova.tm.entity.enumeration;

import org.jetbrains.annotations.NotNull;

public enum RoleType {
    ADMIN("admin"),
    USER("user");

    @NotNull private final String displayName;

    RoleType(@NotNull final String display) {
        this.displayName = display;
    }


    @NotNull
    @Override
    public String toString() {
        return this.displayName;
    }
}
