package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectService{

    @Nullable
    Project persistProject(@NotNull String userId,
                           @NotNull String projectName,
                           @NotNull String description,
                           @NotNull String dateStart,
                           @NotNull String dateFinish);

    void removeProject(@NotNull String userId,
                       @NotNull String projectName);

    void removeAllProjects(@NotNull String userId);

    @Nullable
    List<Project> findAllProjects(@NotNull String userId);

    @Nullable
    Project findOneProject(@NotNull String userId,
                           @NotNull String projectName,
                           @NotNull String projectDescription);

    void mergeProject(@NotNull String userId,
                      @NotNull String oldProjectName,
                      @NotNull String projectName,
                      @NotNull String description,
                      @NotNull String dateStart,
                      @NotNull String dateFinish);

    @Nullable
    List<Project> sortProjectsByDateCreated(@NotNull String userId);

    @Nullable
    List<Project> sortProjectsByDateStart(@NotNull String userId);

    @Nullable
    List<Project> sortProjectsByDateFinish(@NotNull String userId);

    @Nullable
    List<Project> sortProjectsByStatus(@NotNull String userId);
}
