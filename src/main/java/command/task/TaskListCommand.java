package command.task;

import command.AbstractCommand;
import service.TaskRepositoryService;

import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private TaskRepositoryService service = new TaskRepositoryService();


    @Override
    public String command() {
        return "task list";
    }

    @Override
    public String description() {
        return "command to print out task list";
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("project id");
        String projectId = in.nextLine();
        String result = service.print(projectId);
        if (result != null) {
            System.out.println(result);
        }
    }
}
