import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class ProjectTest {

    @Inject
    private IProjectService projectService;

    @Inject
    private IUserService userService;

    private String userId = null;

    public void signIn() throws Exception {
        if (userId == null) {
            User user = userService.signIn("test", "test");
            userId = user.getId();
        }

    }

    @Test
    public void t1_persist() throws Exception {
        signIn();
        Project project = projectService.persistProject(userId,
                "test", "des", "20.02.2020", "20.02.2020");
    }

    @Test
    public void t2_merge() throws Exception {
        signIn();
        projectService.mergeProject(userId, "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void t3_findOne() throws Exception {
        signIn();
        Project project = projectService.findOneProject(userId, "test");
    }

    @Test
    public void t4_findAll() throws Exception {
        signIn();
        List<Project> list = projectService.findAllProjects(userId);
    }

    @Test
    public void t5_remove() throws Exception {
        signIn();
        projectService.removeProject(userId, "test");
    }

    @Test
    public void t6_removeAll() throws Exception {
        signIn();
        projectService.removeAllProjects(userId);
    }

}
