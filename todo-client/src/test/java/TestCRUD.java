import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

    @Nullable
    private Session session;

    public void signIn() throws Exception {
        if (this.session == null) {
            @Nullable final Session session = sessionService.signIn("test", "test");
            Assert.assertNotNull(session);
            this.session = session;
        }
    }

    @Ignore
    @Test
    public void t01_changePassword() throws Exception {
        signIn();
        userService.changePassword(session, "test", "test", "TEST");
        @Nullable final Session testSession = sessionService.signIn("test", "test");
        Assert.assertNull(testSession);
        sessionService.remove(session);
    }

    @Test
    public void t02_updateUser() throws Exception {
        signIn();
        userService.updateUser(session, "FN", "LN", "EMAIL");
        @Nullable final UserDto user = userService.findUser(session);
        Assert.assertNotNull(user);
        Assert.assertEquals("FN", user.getFirstName());
        sessionService.remove(session);
    }

    @Test
    public void t03_persistProject() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.persistProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }

    @Test
    public void t04_mergeProject() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "test", "DESCRIPTION", "20.02.2020", "20.02.2020", "done");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("DESCRIPTION", project.getDescription());
        sessionService.remove(session);
    }

    @Test
    public void t05_findOneProject() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }

    @Test
    public void t06_findAllProjects() throws Exception {
        signIn();
        @Nullable final List<ProjectDto> list = projectService.findAllProjects(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
        sessionService.remove(session);
    }

    @Test
    public void t07_persistTask() throws Exception {
        signIn();
        @Nullable final TaskDto task = taskService.persistTask(session, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
        sessionService.remove(session);
    }

    @Test
    public void t08_findOneTask() throws Exception {
        signIn();
        @Nullable final TaskDto task = taskService.findOneTask(session, "test", "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
        sessionService.remove(session);
    }

    @Test
    public void t09_findAllTasks() throws Exception {
        signIn();
        @Nullable final List<TaskDto> list = taskService.findAllTasks(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
        sessionService.remove(session);
    }

    @Test
    public void t10_findAllTasksInProject() throws Exception {
        signIn();
        @Nullable final List<TaskDto> list = taskService.findAllTasksInProject(session, "test");
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
        sessionService.remove(session);
    }

    @Test
    public void t11_mergeTask() throws Exception {
        signIn();
        taskService.mergeTask(session, "test", "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final TaskDto task = taskService.findOneTask(session, "test", "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("DONE", task.getStatus().toString());
        sessionService.remove(session);
    }

    @Test
    public void t12_removeTask() throws Exception {
        signIn();
        taskService.removeTask(session, "test", "test");
        @Nullable final TaskDto task = taskService.findOneTask(session, "test", "test");
        Assert.assertNull(task);
        sessionService.remove(session);
    }

    @Test
    public void t13_removeAllTasksInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(session, "test");
        @Nullable final List<TaskDto> list = taskService.findAllTasksInProject(session, "test");
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }

    @Test
    public void t14_removeAllTasks() throws Exception {
        signIn();
        taskService.removeAllTasks(session);
        @Nullable final List<TaskDto> list = taskService.findAllTasks(session);
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }

    @Test
    public void t15_removeProject() throws Exception {
        signIn();
        projectService.removeProject(session, "test");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNull(project);
        sessionService.remove(session);
    }

    @Test
    public void t16_removeAllProjects() throws Exception {
        signIn();
        projectService.removeAllProjects(session);
        @Nullable final List<ProjectDto> list = projectService.findAllProjects(session);
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }

}
