package command.project;

import command.AbstractCommand;
import service.ProjectRepositoryService;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {
    ProjectRepositoryService service = new ProjectRepositoryService();
    Scanner in = new Scanner(System.in);

    @Override
    public String command() {
        return "remove project";
    }

    @Override
    public String description() {
        return "command to remove project by id";
    }

    @Override
    public void execute() {
        System.out.println("project id");
        String projectId = in.nextLine();
        String result = service.deleteProject(projectId);
        if (result != null) {
            System.out.println(result);
        }
    }
}
