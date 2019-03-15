package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
public class Session {

    @Nullable
    private String id;

    @Nullable
    private String signature;

    @Nullable
    private Date date;
}
