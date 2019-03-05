package ru.zagorodnikova.tm.entity;

public enum RoleType {
    ADMIN,
    USER;

    public String displayName() {
        switch (this) {
            case ADMIN:
                return "admin";
            case USER:
                return "user";
        }
        return null;
    }

}
