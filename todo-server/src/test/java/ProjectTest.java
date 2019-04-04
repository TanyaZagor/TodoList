import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class ProjectTest {

    @Inject
    private IProjectService projectService;

    private String userId = "e7e8f51a-5ea8-464b-a0cb-2f903e120b10";

    @Test
    public void persist() throws Exception {
        Project project = projectService.persistProject(userId,
                "test", "des", "20.02.2020", "20.02.2020");
        if (project != null) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void merge() throws Exception {
        projectService.mergeProject(userId, "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void findOne() {
        Project project = projectService.findOneProject(userId, "test");
        if (project != null) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void findAll() throws Exception {
        List<Project> list = projectService.findAllProjects(userId);
        if (list != null) {
            list.forEach(v -> System.out.println(v.getName()));
        }
    }

    @Test
    public void remove() throws Exception {
        projectService.removeProject(userId, "test");
    }

    @Test
    public void removeAll() throws Exception {
        projectService.removeAllProjects(userId);
    }

}
