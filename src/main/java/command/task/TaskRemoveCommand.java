package command.task;

import command.AbstractCommand;
import service.TaskRepositoryService;

import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {
    private TaskRepositoryService service = new TaskRepositoryService();

    @Override
    public String command() {
        return "remove task";
    }

    @Override
    public String description() {
        return "command to remove task by id";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project id");
        String projectId = in.nextLine();
        System.out.println("task id");
        String taskId = in.nextLine();
        String result = service.deleteTask(projectId, taskId);
        if (result != null) {
            System.out.println(result);
        }
    }
}
