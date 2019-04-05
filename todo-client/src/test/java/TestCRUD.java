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
    public void t01_changePassword() throws Exception {
        signIn();
        userService.changePassword(session, "test", "test", "test");
        sessionService.remove(session);
    }

    @Test
    public void t02_updateUser() throws Exception {
        signIn();
        userService.updateUser(session, "FN", "LN", "EMAIL");
        sessionService.remove(session);
    }

    @Test
    public void t03_persistProject() throws Exception {
        signIn();
        ProjectDto project = projectService.persistProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }

    @Test
    public void t04_mergeProject() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "test", "DESCRIPTION", "20.02.2020", "20.02.2020", "done");
        sessionService.remove(session);
    }

    @Test
    public void t05_findOneProject() throws Exception {
        signIn();
        ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }

    @Test
    public void test06findAllProjects() throws Exception {
        signIn();
        List<ProjectDto> list = projectService.findAllProjects(session);
        sessionService.remove(session);
    }

    @Test
    public void t07_persistTask() throws Exception {
        signIn();
        TaskDto task = taskService.persistTask(session, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", task.getName());
        sessionService.remove(session);
    }

    @Test
    public void t08_findOneTask() throws Exception {
        signIn();
        TaskDto task = taskService.findOneTask(session, "test", "test");
        Assert.assertEquals("test", task.getName());
        sessionService.remove(session);
    }

    @Test
    public void t09_findAllTasks() throws Exception {
        signIn();
        List<TaskDto> list = taskService.findAllTasks(session);
        sessionService.remove(session);
    }

    @Test
    public void t10_findAllTasksInProject() throws Exception {
        signIn();
        taskService.findAllTasksInProject(session, "test");
        sessionService.remove(session);
    }

    @Test
    public void t11_mergeTask() throws Exception {
        signIn();
        taskService.mergeTask(session, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
        sessionService.remove(session);
    }

    @Test
    public void t12_removeTask() throws Exception {
        signIn();
        taskService.removeTask(session, "test", "TTT");
        sessionService.remove(session);
    }

    @Test
    public void t13_removeAllTasksInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(session, "test");
        sessionService.remove(session);
    }

    @Test
    public void t14_removeAllTasks() throws Exception {
        signIn();
        taskService.removeAllTasks(session);
        sessionService.remove(session);
    }

    @Test
    public void t15_removeProject() throws Exception {
        signIn();
        projectService.removeProject(session, "test");
        sessionService.remove(session);
    }

    @Test
    public void t16_removeAllProjects() throws Exception {
        signIn();
        projectService.removeAllProjects(session);
        sessionService.remove(session);
    }

}
