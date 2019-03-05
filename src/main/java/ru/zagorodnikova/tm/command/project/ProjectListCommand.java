package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Map;

public class ProjectListCommand extends AbstractCommand {

    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "project list";
    }

    @Override
    public String description() {
        return "print out project list";
    }

    @Override
    public void execute() {

        Map<String, Project> projects = getBootstrap().getProjectService().print();
        if (!(projects == null || projects.isEmpty())) {
            projects.forEach((k, v) -> System.out.println(v));
        }
    }
}
