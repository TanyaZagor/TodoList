package ru.zagorodnikova.tm;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.service.Project;
import ru.zagorodnikova.tm.api.service.Task;
import ru.zagorodnikova.tm.api.service.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Domain implements Serializable {

    @Nullable
    private User user;

    @NotNull
    @XmlElement(name = "project")
    private List<Project> projects = new ArrayList<>();

    @NotNull
    @XmlElement(name = "task")
    private List<Task> tasks = new ArrayList<>();

}
