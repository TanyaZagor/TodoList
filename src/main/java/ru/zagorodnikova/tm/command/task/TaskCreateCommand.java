package ru.zagorodnikova.tm.command.task;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.service.TaskRepositoryService;
import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private String projectId;
    private String taskId;
    private String taskName;
    private String description;
    private String dateFinish;

    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }


    @Override
    public String command() {
        return "create task";
    }

    @Override
    public String description() {
        return "command to create task";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project id");
        projectId = in.nextLine();
        System.out.println("task id");
        taskId = in.nextLine();
        System.out.println("task name");
        taskName = in.nextLine();
        System.out.println("task description");
        description = in.nextLine();
        System.out.println("task data finish");
        dateFinish = in.nextLine();
        String result = super.getBootstrap().getTaskRepositoryService().addTask(projectId, taskName, description, dateFinish);
        if (result != null) {
            System.out.println(result);
        }
    }
}
