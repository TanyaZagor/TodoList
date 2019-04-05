import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCRUD {
    @Inject
    private IProjectService projectService;

    @Inject
    private IUserService userService;

    @Inject
    private ITaskService taskService;

    private String userId = null;

    @Test
    public void signUp() throws Exception {
        User user = userService.signUp("test", "test", "fn", "ln", "email");
    }

    @Test
    public void signIn() throws Exception {
        if (userId == null) {
            User user = userService.signIn("test", "test");
            Assert.assertNotNull(user);
            userId = user.getId();
        }
    }

    @Ignore
    @Test
    public void changePassword() throws Exception {
        signIn();
        userService.changePassword(userId, "test", "test", "TEST");
    }

    @Test
    public void t01_updateUser() throws Exception {
        signIn();
        userService.updateUser(userId, "FN", "LN", "EMAIL");
        @Nullable final User user = userService.findOne(userId);
        Assert.assertNotNull(user);
        Assert.assertEquals("FN", user.getFirstName());
    }

    @Test
    public void t02_persistProject() throws Exception {
        signIn();
        Project project = projectService.persistProject(userId,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void t03_mergeProject() throws Exception {
        signIn();
        projectService.mergeProject(userId, "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final Project project = projectService.findOneProject(userId, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("done", project.getStatus().toString());
    }

    @Test
    public void t04_findOneProject() throws Exception {
        signIn();
        Project project = projectService.findOneProject(userId, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void t05_findAllProjects() throws Exception {
        signIn();
        @Nullable final List<Project> list = projectService.findAllProjects(userId);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void t06_persistTask() throws Exception {
        signIn();
        Task task = taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void t07_findOneTask() throws Exception {
        signIn();
        Task task = taskService.findOneTask(userId, "test",
                "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void t08_findAllTasks() throws Exception {
        signIn();
        @Nullable final List<Task> list = taskService.findAllTasks(userId);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void t09_findAllTasksInProject() throws Exception {
        signIn();
        @Nullable final List<Task> list = taskService.findAllTasksInProject(userId, "test");
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void t10_mergeTask() throws Exception {
        signIn();
        taskService.mergeTask(userId, "test", "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final Task task = taskService.findOneTask(userId, "test", "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("done", task.getStatus().toString());
    }

    @Test
    public void t11_removeTask() throws Exception {
        signIn();
        taskService.removeTask(userId, "test", "test");
        @Nullable final Task task = taskService.findOneTask(userId, "test", "test");
        Assert.assertNull(task);
    }

    @Test
    public void t12_removeAllTasksInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(userId, "test");
        @Nullable final List<Task> list = taskService.findAllTasksInProject(userId, "test");
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void t13_removeAllTasks() throws Exception {
        signIn();
        taskService.removeAllTasks(userId);
        @Nullable final List<Task> list = taskService.findAllTasks(userId);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void t14_removeProject() throws Exception {
        signIn();
        projectService.removeProject(userId, "test");
        @Nullable final Project project = projectService.findOneProject(userId, "test");
        Assert.assertNull(project);
    }

    @Test
    public void t15_removeAllProjects() throws Exception {
        signIn();
        projectService.removeAllProjects(userId);
        @Nullable final List<Project> list = projectService.findAllProjects(userId);
        Assert.assertTrue(list.isEmpty());
    }
}
