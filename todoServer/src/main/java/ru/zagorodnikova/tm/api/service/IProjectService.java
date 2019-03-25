package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectService{

    @Nullable
    Project persistProject(@NotNull final String userId,
                           @NotNull final String projectName,
                           @NotNull final String description,
                           @NotNull final String dateStart,
                           @NotNull final String dateFinish) throws Exception;

    void removeProject(@NotNull final String userId,
                       @NotNull final String projectName) throws Exception;

    void removeAllProjects(@NotNull final String userId) throws Exception;

    @Nullable
    List<Project> findAllProjects(@NotNull final String userId) throws Exception;

    @Nullable
    Project findOneProject(@NotNull final String userId,
                           @NotNull final String projectName);

    void mergeProject(@NotNull final String userId,
                      @NotNull final String oldProjectName,
                      @NotNull final String projectName,
                      @NotNull final String description,
                      @NotNull final String dateStart,
                      @NotNull final String dateFinish) throws Exception;

    @Nullable
    List<Project> sortProjectsByDateCreated(@NotNull final String userId) throws Exception;

    @Nullable
    List<Project> sortProjectsByDateStart(@NotNull final String userId) throws Exception;

    @Nullable
    List<Project> sortProjectsByDateFinish(@NotNull final String userId) throws Exception;

    @Nullable
    List<Project> sortProjectsByStatus(@NotNull final String userId) throws Exception;
}
