import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.ApplicationConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskTest {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    private String userId = null;

    public void signIn() throws Exception {
        if (userId == null) {
            User user = userService.signIn("test", "test");
            Assert.assertNotNull(user);
            userId = user.getId();
        }
    }

    @Test
    public void t1_persist() throws Exception {
        signIn();
        @Nullable final Task task = taskService.persistTask(userId, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void t2_findOne() throws Exception {
        signIn();
        @Nullable final Task task = taskService.findOneTask(userId, "test",
                "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void t3_findAll() throws Exception {
        signIn();
        @Nullable final List<Task> list = taskService.findAllTasks(userId);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void t4_findAllInProject() throws Exception {
        signIn();
        @Nullable final List<Task> list = taskService.findAllTasksInProject(userId, "test");
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void t5_merge() throws Exception {
        signIn();
        taskService.mergeTask(userId, "test", "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final Task task = taskService.findOneTask(userId, "test", "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("done", task.getStatus().toString());
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
