import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.endpoint.TaskDto;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskTest {

    @Inject
    private TaskEndpoint taskService;

    @Inject
    private SessionEndpoint sessionService;

    @Nullable
    private Session session;

    public void signIn() throws Exception {
        if (this.session == null) {
            @Nullable final Session session = sessionService.signIn("test", "test");
            if (session != null) {
                this.session = session;
            }
        }
    }

    @Test
    public void t1_persist() throws Exception {
        signIn();
        @Nullable final TaskDto task = taskService.persistTask(session, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        if (task != null) {
            Assert.assertEquals("test", task.getName());
        }
        sessionService.remove(session);
    }

    @Test
    public void t2_findOne() throws Exception {
        signIn();
        @Nullable final TaskDto task = taskService.findOneTask(session, "test", "test");
        if (task != null) {
            Assert.assertEquals("test", task.getName());
        }
        sessionService.remove(session);
    }

    @Test
    public void t3_findAll() throws Exception {
        signIn();
        @Nullable final List<TaskDto> list = taskService.findAllTasks(session);
        if (list != null) {
            Assert.assertEquals("test", list.get(0).getName());
        }
        sessionService.remove(session);
    }

    @Test
    public void t4_findAllInProject() throws Exception {
        signIn();
        @Nullable final List<TaskDto> list = taskService.findAllTasksInProject(session, "test");
        if (list != null) {
            Assert.assertEquals("test", list.get(0).getName());
        }
        sessionService.remove(session);
    }

    @Test
    public void t5_merge() throws Exception {
        signIn();
        taskService.mergeTask(session, "test", "test", "test", "des",
                "20.02.2020", "20.02.2020", "done");
        @Nullable final TaskDto task = taskService.findOneTask(session, "test", "test");
        if (task != null) {
            Assert.assertEquals("DONE", task.getStatus().toString());
        }
        sessionService.remove(session);
    }

    @Test
    public void t6_remove() throws Exception {
        signIn();
        taskService.removeTask(session, "test", "test");
        @Nullable final TaskDto task = taskService.findOneTask(session, "test", "test");
        Assert.assertNull(task);
        sessionService.remove(session);
    }

    @Test
    public void t7_removeAllInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(session, "test");
        @Nullable final List<TaskDto> list = taskService.findAllTasksInProject(session, "test");
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }

    @Test
    public void t8_removeAll() throws Exception {
        signIn();
        taskService.removeAllTasks(session);
        @Nullable final List<TaskDto> list = taskService.findAllTasks(session);
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }

}
