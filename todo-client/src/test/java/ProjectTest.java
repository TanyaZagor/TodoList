import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.endpoint.ProjectDto;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;

import javax.inject.Inject;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

    @Inject
    private ProjectEndpoint projectService;

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
        @Nullable final ProjectDto project = projectService.persistProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        if (project != null) {
            Assert.assertEquals("test", project.getName());
        }
        sessionService.remove(session);
    }


    @Test
    public void t2_merge() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        assert project != null;
        Assert.assertEquals("DONE", project.getStatus().toString());
        sessionService.remove(session);
    }

    @Test
    public void t3_findOne() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        if (project != null) {
            Assert.assertEquals("test", project.getName());
        }
        sessionService.remove(session);
    }

    @Test
    public void t4_findAll() throws Exception {
        signIn();
        @Nullable final List<ProjectDto> list = projectService.findAllProjects(session);
        if (list != null) {
            Assert.assertEquals("test", list.get(0).getName());
        }
        sessionService.remove(session);
    }

    @Test
    public void t5_remove() throws Exception {
        signIn();
        projectService.removeProject(session, "test");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNull(project);
        sessionService.remove(session);
    }

    @Test
    public void t6_removeAll() throws Exception {
        signIn();
        projectService.removeAllProjects(session);
        @Nullable final List<ProjectDto> list = projectService.findAllProjects(session);
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }

}
