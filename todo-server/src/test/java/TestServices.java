import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class TestServices {
    @Inject
    private IProjectService projectService;

    @Inject
    private IUserService userService;

    @Inject
    private ITaskService taskService;

    private String userId = "e7e8f51a-5ea8-464b-a0cb-2f903e120b10";

    @Test
    public void signUp() throws Exception {
        User user = userService.signUp("test", "test", "fn", "ln", "email");
        if (user != null) {
            System.out.println(user.getLogin());
        }
    }

    @Test
    public void signIn() throws Exception {
        User user = userService.signIn("test", "test");
        if (user != null) {
            System.out.println(user.getLogin());
        }
    }

    @Test
    public void changePassword() throws Exception {
        userService.changePassword(userId, "test", "test", "test");
    }

    @Test
    public void updateUser() throws Exception {
        userService.updateUser(userId, "FN", "LN", "EMAIL");
    }

    @Test
    public void persistProject() throws Exception {
        Project project = projectService.persistProject(userId,
                "TTT", "des", "20.02.2020", "20.02.2020");
        if (project != null) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void mergeProject() throws Exception {
        projectService.mergeProject(userId, "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void findOneProject() {
        Project project = projectService.findOneProject(userId, "test");
        if (project != null) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void findAllProjects() throws Exception {
        List<Project> list = projectService.findAllProjects(userId);
        if (list != null) {
            list.forEach(v -> System.out.println(v.getName()));
        }
    }

    @Test
    public void persistTask() throws Exception {
        taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
    }

    @Test
    public void findOneTask() {
        Task task = taskService.findOneTask(userId, "test",
                "test");
        if (task != null) {
            System.out.println(task.getName());
        }
    }

    @Test
    public void findAllTasks() {
        List<Task> list = taskService.findAllTasks(userId);
        if (list != null) {
            list.forEach(v -> System.out.println(v.getName()));
        }
    }

    @Test
    public void findAllTasksInProject() {
        taskService.findAllTasksInProject(userId, "test");
    }

    @Test
    public void mergeTask() throws Exception {
        taskService.mergeTask(userId, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void removeTask() throws Exception {
        taskService.removeTask(userId, "test", "TTT");
    }

    @Test
    public void removeAllTasksInProject() throws Exception {
        taskService.removeAllTasksInProject(userId, "test");
    }

    @Test
    public void removeAllTasks() throws Exception {
        taskService.removeAllTasks(userId);
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
