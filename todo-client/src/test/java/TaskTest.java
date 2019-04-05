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
    public void persist() throws Exception {
        signIn();
        taskService.persistTask(session, "test", "test",
                "des", "20.02.2020", "20.02.2020");
    }

    @Test
    public void findOne() throws Exception {
        signIn();
        TaskDto task = taskService.findOneTask(session, "test",
                "test");
    }

    @Test
    public void findAll() throws Exception {
        signIn();
        List<TaskDto> list = taskService.findAllTasks(session);
    }

    @Test
    public void findAllInProject() throws Exception {
        signIn();
        taskService.findAllTasksInProject(session, "test");
    }

    @Test
    public void merge() throws Exception {
        signIn();
        taskService.mergeTask(session, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void remove() throws Exception {
        signIn();
        taskService.removeTask(session, "test", "TTT");
    }

    @Test
    public void removeAllInProject() throws Exception {
        signIn();
        taskService.removeAllTasksInProject(session, "test");
    }

    @Test
    public void removeAll() throws Exception {
        signIn();
        taskService.removeAllTasks(session);
    }

}
