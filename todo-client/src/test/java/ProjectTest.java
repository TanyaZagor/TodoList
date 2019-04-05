import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.endpoint.ProjectDto;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class ProjectTest {

    @Inject
    private ProjectEndpoint projectService;

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
        ProjectDto project = projectService.persistProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }


    @Test
    public void t2_merge() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "TTT", "des", "20.02.2020", "20.02.2020", "done");
        sessionService.remove(session);
    }

    @Test
    public void t3_findOne() throws Exception {
        signIn();
        ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }

    @Test
    public void t4_findAll() throws Exception {
        signIn();
        List<ProjectDto> list = projectService.findAllProjects(session);
        sessionService.remove(session);
    }

    @Test
    public void t5_remove() throws Exception {
        signIn();
        projectService.removeProject(session, "test");
        sessionService.remove(session);
    }

    @Test
    public void t6_removeAll() throws Exception {
        signIn();
        projectService.removeAllProjects(session);
        sessionService.remove(session);
    }

}
