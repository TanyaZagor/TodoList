package ru.zagorodnikova.tm.entity;

public enum RoleType {
    ADMIN("admin"),
    USER("user");

    private final String displayName;

    RoleType(final String display) {
        this.displayName = display;
    }


    @Override
    public String toString() {
        return this.displayName;
    }
}
