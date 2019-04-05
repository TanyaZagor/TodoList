import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        @Nullable final Task task = taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        if (task != null) {
            Assert.assertEquals("test", task.getName());
        }
    }

    @Test
    public void t2_findOne() throws Exception {
        signIn();
        @Nullable final Task task = taskService.findOneTask(userId, "test",
                "test");
        if (task != null) {
            Assert.assertEquals("test", task.getName());
        }
    }

    @Test
    public void t3_findAll() throws Exception {
        signIn();
        @Nullable final List<Task> list = taskService.findAllTasks(userId);
        if (list != null) {
            Assert.assertEquals("test", list.get(0).getName());
        }
    }

    @Test
    public void t4_findAllInProject() throws Exception {
        signIn();
        @Nullable final List<Task> list = taskService.findAllTasksInProject(userId, "test");
        if (list != null) {
            Assert.assertEquals("test", list.get(0).getName());
        }
    }

    @Test
    public void t5_merge() throws Exception {
        signIn();
        taskService.mergeTask(userId, "test", "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final Task task = taskService.findOneTask(userId, "test", "test");
        if (task != null) {
            Assert.assertEquals("done", task.getStatus().toString());
        }
    }

    @Test
    public void t6_remove() throws Exception {
        signIn();
        taskService.removeTask(userId, "test", "test");
        @Nullable final Task task = taskService.findOneTask(userId, "test", "test");
        Assert.assertNull(task);
    }

    @Test
    public void t7_removeAllInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(userId, "test");
        @Nullable final List<Task> list = taskService.findAllTasksInProject(userId, "test");
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void t8_removeAll() throws Exception {
        signIn();
        taskService.removeAllTasks(userId);
        @Nullable final List<Task> list = taskService.findAllTasks(userId);
        Assert.assertTrue(list.isEmpty());
    }

}
