package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "app_project")
public class Project extends AbstractEntity {


    @NotNull
    @Id
    private String id = super.getId();

    @Nullable
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private String userId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateFinish;

    @Nullable
    private Date dateStart;

    @NotNull
    private Date dateCreate = new Date();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Project(@NotNull String userId, @NotNull String name, @NotNull String description,
                   @NotNull Date dateStart, @NotNull Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Project project = (Project) o;
        return Objects.equals(userId, project.userId) &&
                Objects.equals(name, project.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }
}
