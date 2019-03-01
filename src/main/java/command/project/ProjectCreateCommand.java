package command.project;

import command.AbstractCommand;
import service.ProjectRepositoryService;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    ProjectRepositoryService service = new ProjectRepositoryService();
    private Scanner in = new Scanner(System.in);

    @Override
    public String command() {
        return "create project";
    }

    @Override
    public String description() {
        return "command to create project";
    }

    @Override
    public void execute() {
        System.out.println("project id");
        String projectId = in.nextLine();
        System.out.println("project name");
        String projectName = in.nextLine();
        System.out.println("project description");
        String description = in.nextLine();
        String result = service.addProject(projectId, projectName, description);
        if (result != null) {
            System.out.println(result);
        }
    }
}
