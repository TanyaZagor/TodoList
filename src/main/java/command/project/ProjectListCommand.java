package command.project;

import command.AbstractCommand;
import service.ProjectRepositoryService;

public class ProjectListCommand extends AbstractCommand {
    ProjectRepositoryService service = new ProjectRepositoryService();

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
        service.print();
    }
}
