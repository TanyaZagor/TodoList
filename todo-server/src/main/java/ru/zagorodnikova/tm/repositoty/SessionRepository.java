package ru.zagorodnikova.tm.repositoty;

import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zagorodnikova.tm.entity.Session;

public interface SessionRepository extends JpaRepository<Session, String> {

    Session save(@NotNull final Session session);

    void delete(@NotNull final Session session);

    @Nullable
    @Query(value = "SELECT session FROM Session session WHERE session.id = :id")
    Session findOne(@NotNull @Param("id") final String id);
}
