package ru.zagorodnikova.tm;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.endpoint.Project;
import ru.zagorodnikova.tm.endpoint.Task;
import ru.zagorodnikova.tm.endpoint.User;

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

    @NotNull
    @XmlElement(name = "project")
    private List<Project> projects = new ArrayList<>();

    @NotNull
    @XmlElement(name = "task")
    private List<Task> tasks = new ArrayList<>();

}
