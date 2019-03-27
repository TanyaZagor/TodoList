package ru.zagorodnikova.tm.util;

import org.jetbrains.annotations.NotNull;

public class PasswordUtil {

    @NotNull
    public static String hashPassword(@NotNull final String password) throws Exception {
        @NotNull final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        @NotNull final byte[] array = md.digest(password.getBytes());
        @NotNull final StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }
}