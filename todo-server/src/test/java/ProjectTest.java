import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

    @Inject
    private IProjectService projectService;

    @Inject
    private IUserService userService;

    private String userId = null;

    public void signIn() throws Exception {
        if (userId == null) {
            @Nullable final User user = userService.signIn("test", "test");
            if (user != null) {
                userId = user.getId();
            }
        }

    }

    @Test
    public void t1_persist() throws Exception {
        signIn();
        @Nullable final Project project = projectService.persistProject(userId,
                "test", "des", "20.02.2020", "20.02.2020");
        if (project != null) {
            Assert.assertEquals("test", project.getName());
        }
    }

    @Test
    public void t2_merge() throws Exception {
        signIn();
        projectService.mergeProject(userId, "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final Project project = projectService.findOneProject(userId, "test");
        if (project != null) {
            Assert.assertEquals("done", project.getStatus().toString());
        }
    }

    @Test
    public void t3_findOne() throws Exception {
        signIn();
        @Nullable final Project project = projectService.findOneProject(userId, "test");
        if (project != null) {
            Assert.assertEquals("test", project.getName());
        }
    }

    @Test
    public void t4_findAll() throws Exception {
        signIn();
        @Nullable final List<Project> list = projectService.findAllProjects(userId);
        if (list != null) {
            Assert.assertEquals("test", list.get(0).getName());
        }
    }

    @Test
    public void t5_remove() throws Exception {
        signIn();
        projectService.removeProject(userId, "test");
        @Nullable final Project project = projectService.findOneProject(userId, "test");
        Assert.assertNull(project);
    }

    @Test
    public void t6_removeAll() throws Exception {
        signIn();
        projectService.removeAllProjects(userId);
        @Nullable final List<Project> list = projectService.findAllProjects(userId);
        Assert.assertTrue(list.isEmpty());
    }

}
