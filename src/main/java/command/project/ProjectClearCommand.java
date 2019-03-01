package command.project;

import command.AbstractCommand;
import service.ProjectRepositoryService;


public class ProjectClearCommand extends AbstractCommand {
    ProjectRepositoryService service = new ProjectRepositoryService();

    @Override
    public String command() {
        return "clear projects";
    }

    @Override
    public String description() {
        return "command to clear projects by id";
    }

    @Override
    public void execute() {
        service.deleteAll();
    }
}
