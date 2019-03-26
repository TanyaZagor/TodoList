package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Session extends AbstractEntity implements Cloneable {

    @NotNull
    private String id = super.getId();

    @NotNull
    private String userId;

    @Nullable
    private String signature;

    @NotNull
    private long timestamp = new Date().getTime();

    public Session(@NotNull String userId) {
        this.userId = userId;
    }

    @Nullable
    public Session clone() {
        try {
            return (Session) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
