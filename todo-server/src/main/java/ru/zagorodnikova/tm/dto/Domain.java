package ru.zagorodnikova.tm.dto;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@XmlRootElement
public class Domain implements Serializable {

    @NotNull
    private List<User> users =new ArrayList<>();

    @NotNull
    private List<Project> projects = new ArrayList<>();

    @NotNull
    private List<Task> tasks = new ArrayList<>();

}
