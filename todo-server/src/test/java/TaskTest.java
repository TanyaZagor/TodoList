import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class TaskTest {

    @Inject
    private ITaskService taskService;

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
        taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
    }

    @Test
    public void t2_findOne() {
        Task task = taskService.findOneTask(userId, "test",
                "test");
    }

    @Test
    public void t3_findAll() {
        List<Task> list = taskService.findAllTasks(userId);
    }

    @Test
    public void t4_findAllInProject() {
        taskService.findAllTasksInProject(userId, "test");
    }

    @Test
    public void t5_merge() throws Exception {
        taskService.mergeTask(userId, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void t6_remove() throws Exception {
        taskService.removeTask(userId, "test", "TTT");
    }

    @Test
    public void t7_removeAllInProject() throws Exception {
        taskService.removeAllTasksInProject(userId, "test");
    }

    @Test
    public void t8_removeAll() throws Exception {
        taskService.removeAllTasks(userId);
    }

}
