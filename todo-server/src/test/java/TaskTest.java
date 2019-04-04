import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Task;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class TaskTest {

    @Inject
    private ITaskService taskService;

    private String userId = "e7e8f51a-5ea8-464b-a0cb-2f903e120b10";

    @Test
    public void persist() throws Exception {
        taskService.persistTask(userId, "project", "test",
                "des", "20.02.2020", "20.02.2020");
    }

    @Test
    public void findOne() {
        Task task = taskService.findOneTask(userId, "test",
                "test");
        if (task != null) {
            System.out.println(task.getName());
        }
    }

    @Test
    public void findAll() {
        List<Task> list = taskService.findAllTasks(userId);
        if (list != null) {
            list.forEach(v -> System.out.println(v.getName()));
        }
    }

    @Test
    public void findAllInProject() {
        taskService.findAllTasksInProject(userId, "test");
    }

    @Test
    public void merge() throws Exception {
        taskService.mergeTask(userId, "test", "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
    }

    @Test
    public void remove() throws Exception {
        taskService.removeTask(userId, "test", "TTT");
    }

    @Test
    public void removeAllInProject() throws Exception {
        taskService.removeAllTasksInProject(userId, "test");
    }

    @Test
    public void removeAll() throws Exception {
        taskService.removeAllTasks(userId);
    }

}
