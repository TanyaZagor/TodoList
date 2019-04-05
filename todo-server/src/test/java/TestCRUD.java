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
    public void test01updateUser() throws Exception {
        signIn();
        userService.updateUser(userId, "FN", "LN", "EMAIL");
    }

    @Test
    public void test02persistProject() throws Exception {
        signIn();
        Project project = projectService.persistProject(userId,
                "TTT", "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("TTT", project.getName());
    }

    @Test
    public void test03mergeProject() throws Exception {
        signIn();
        projectService.mergeProject(userId, "TTT",
                "test", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void test04findOneProject() throws Exception {
        signIn();
        Project project = projectService.findOneProject(userId, "test");
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void test05findAllProjects() throws Exception {
        signIn();
        List<Project> list = projectService.findAllProjects(userId);
    }

    @Test
    public void test06persistTask() throws Exception {
        signIn();
        Task task = taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void test07findOneTask() throws Exception {
        signIn();
        Task task = taskService.findOneTask(userId, "test",
                "test");
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void test08findAllTasks() throws Exception {
        signIn();
        List<Task> list = taskService.findAllTasks(userId);
    }

    @Test
    public void test09findAllTasksInProject() throws Exception {
        signIn();
        taskService.findAllTasksInProject(userId, "test");
    }

    @Test
    public void test10mergeTask() throws Exception {
        signIn();
        taskService.mergeTask(userId, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void test11testremoveTask() throws Exception {
        signIn();
        taskService.removeTask(userId, "test", "TTT");
    }

    @Test
    public void test12removeAllTasksInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(userId, "test");
    }

    @Test
    public void test13RemoveAllTasks() throws Exception {
        signIn();
        taskService.removeAllTasks(userId);
    }

    @Test
    public void test14removeProject() throws Exception {
        signIn();
        projectService.removeProject(userId, "test");
    }

    @Test
    public void test15RemoveAllProjects() throws Exception {
        signIn();
        projectService.removeAllProjects(userId);
    }
}
