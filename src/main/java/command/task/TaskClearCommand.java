package command.task;

import command.AbstractCommand;
import service.TaskRepositoryService;

import java.util.Scanner;

public class TaskClearCommand extends AbstractCommand {

    private TaskRepositoryService service = new TaskRepositoryService();

    @Override
    public String command() {
        return "clear task";
    }

    @Override
    public String description() {
        return "command to clear task by id";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project id");
        String projectId = in.nextLine();
        String result = service.deleteAll(projectId);
        if (result != null) {
            System.out.println(result);
        }
    }
}
