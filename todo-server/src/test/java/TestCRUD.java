import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
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
    }

    @Test
    public void t02_persistProject() throws Exception {
        signIn();
        Project project = projectService.persistProject(userId,
                "TTT", "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("TTT", project.getName());
    }

    @Test
    public void t03_mergeProject() throws Exception {
        signIn();
        projectService.mergeProject(userId, "TTT",
                "test", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void t04_findOneProject() throws Exception {
        signIn();
        Project project = projectService.findOneProject(userId, "test");
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void t05_findAllProjects() throws Exception {
        signIn();
        List<Project> list = projectService.findAllProjects(userId);
    }

    @Test
    public void t06_persistTask() throws Exception {
        signIn();
        Task task = taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void t07_findOneTask() throws Exception {
        signIn();
        Task task = taskService.findOneTask(userId, "test",
                "test");
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void t08_findAllTasks() throws Exception {
        signIn();
        List<Task> list = taskService.findAllTasks(userId);
    }

    @Test
    public void t09_findAllTasksInProject() throws Exception {
        signIn();
        taskService.findAllTasksInProject(userId, "test");
    }

    @Test
    public void t10_mergeTask() throws Exception {
        signIn();
        taskService.mergeTask(userId, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void t11_removeTask() throws Exception {
        signIn();
        taskService.removeTask(userId, "test", "TTT");
    }

    @Test
    public void t12_removeAllTasksInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(userId, "test");
    }

    @Test
    public void t13_removeAllTasks() throws Exception {
        signIn();
        taskService.removeAllTasks(userId);
    }

    @Test
    public void t14_removeProject() throws Exception {
        signIn();
        projectService.removeProject(userId, "test");
    }

    @Test
    public void t15_removeAllProjects() throws Exception {
        signIn();
        projectService.removeAllProjects(userId);
    }
}
