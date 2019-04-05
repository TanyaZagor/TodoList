import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.endpoint.*;

import javax.inject.Inject;
import java.lang.Exception;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCRUD {
    @Inject
    private ProjectEndpoint projectService;

    @Inject
    private UserEndpoint userService;

    @Inject
    private TaskEndpoint taskService;

    @Inject
    private SessionEndpoint sessionService;

    private Session session;

    public void signIn() throws Exception {
        if (this.session == null) {
            Session session = sessionService.signIn("test", "test");
            if (session != null) {
                this.session = session;
            }
        }
    }

    @Test
    public void test01changePassword() throws Exception {
        signIn();
        userService.changePassword(session, "test", "test", "test");
    }

    @Test
    public void test02updateUser() throws Exception {
        signIn();
        userService.updateUser(session, "FN", "LN", "EMAIL");
    }

    @Test
    public void test03persistProject() throws Exception {
        signIn();
        ProjectDto project = projectService.persistProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void test04mergeProject() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "test", "DESCRIPTION", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void test05findOneProject() throws Exception {
        signIn();
        ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void test06findAllProjects() throws Exception {
        signIn();
        List<ProjectDto> list = projectService.findAllProjects(session);
    }

    @Test
    public void test07persistTask() throws Exception {
        signIn();
        TaskDto task = taskService.persistTask(session, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void test08findOneTask() throws Exception {
        signIn();
        TaskDto task = taskService.findOneTask(session, "test", "test");
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void test09findAllTasks() throws Exception {
        signIn();
        List<TaskDto> list = taskService.findAllTasks(session);
    }

    @Test
    public void test10findAllTasksInProject() throws Exception {
        signIn();
        taskService.findAllTasksInProject(session, "test");
    }

    @Test
    public void test11mergeTask() throws Exception {
        signIn();
        taskService.mergeTask(session, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void test12removeTask() throws Exception {
        signIn();
        taskService.removeTask(session, "test", "TTT");
    }

    @Test
    public void test13removeAllTasksInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(session, "test");
    }

    @Test
    public void test14pRemoveAllTasks() throws Exception {
        signIn();
        taskService.removeAllTasks(session);
    }

    @Test
    public void test15removeProject() throws Exception {
        signIn();
        projectService.removeProject(session, "test");
    }

    @Test
    public void test16RemoveAllProjects() throws Exception {
        signIn();
        projectService.removeAllProjects(session);
    }
}
