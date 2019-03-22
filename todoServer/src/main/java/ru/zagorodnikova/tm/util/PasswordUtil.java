package ru.zagorodnikova.tm.util;

import org.jetbrains.annotations.NotNull;

public class PasswordUtil {

    @NotNull
    public static String hashPassword(@NotNull final String password) throws Exception {
        final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        final byte[] array = md.digest(password.getBytes());
        final StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }
}