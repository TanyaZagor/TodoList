package command.task;

import command.AbstractCommand;
import service.TaskRepositoryService;

import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private TaskRepositoryService service = new TaskRepositoryService();


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
        String projectId = in.nextLine();
        System.out.println("task id");
        String taskId = in.nextLine();
        System.out.println("task name");
        String taskName = in.nextLine();
        System.out.println("task description");
        String description = in.nextLine();
        System.out.println("task data finish");
        String dateFinish = in.nextLine();
        String result = service.addTask(projectId, taskId, taskName, description, dateFinish);
        if (result != null) {
            System.out.println(result);
        }
    }
}
