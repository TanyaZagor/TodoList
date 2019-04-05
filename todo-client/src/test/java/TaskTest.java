import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.endpoint.TaskDto;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class TaskTest {

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
    public void t1_persist() throws Exception {
        signIn();
        taskService.persistTask(session, "test", "test",
                "des", "20.02.2020", "20.02.2020");
        sessionService.remove(session);
    }

    @Test
    public void t2_findOne() throws Exception {
        signIn();
        TaskDto task = taskService.findOneTask(session, "test", "test");
        sessionService.remove(session);
    }

    @Test
    public void t3_findAll() throws Exception {
        signIn();
        List<TaskDto> list = taskService.findAllTasks(session);
        sessionService.remove(session);
    }

    @Test
    public void t4_findAllInProject() throws Exception {
        signIn();
        taskService.findAllTasksInProject(session, "test");
        sessionService.remove(session);
    }

    @Test
    public void t5_merge() throws Exception {
        signIn();
        taskService.mergeTask(session, "test", "test", "TTT", "des",
                "20.02.2020", "20.02.2020", "done");
        sessionService.remove(session);
    }

    @Test
    public void t6_remove() throws Exception {
        signIn();
        taskService.removeTask(session, "test", "TTT");
        sessionService.remove(session);
    }

    @Test
    public void t7_removeAllInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(session, "test");
        sessionService.remove(session);
    }

    @Test
    public void t8_removeAll() throws Exception {
        signIn();
        taskService.removeAllTasks(session);
        sessionService.remove(session);
    }

}
