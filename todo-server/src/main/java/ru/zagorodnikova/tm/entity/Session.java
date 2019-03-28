package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_session")
public class Session extends AbstractEntity implements Cloneable {

    @NotNull
    @Id
    private String id = super.getId();

    @NotNull
    @Column(name = "user_id")
    private String userId;

    @Nullable
    private String signature;

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
