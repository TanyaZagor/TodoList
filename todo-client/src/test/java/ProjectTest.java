import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.zagorodnikova.tm.endpoint.ProjectDto;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.util.ApplicationConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

    @Autowired
    private ProjectEndpoint projectService;

    @Autowired
    private SessionEndpoint sessionService;

    @Nullable
    private Session session;

    public void signIn() throws Exception {
        if (this.session == null) {
            @Nullable final Session session = sessionService.signIn("test", "test");
            Assert.assertNotNull(session);
            this.session = session;
        }
    }

    @Test
    public void t1_persist() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.persistProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }


    @Test
    public void t2_merge() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("DONE", project.getStatus().toString());
        sessionService.remove(session);
    }

    @Test
    public void t3_findOne() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
        sessionService.remove(session);
    }

    @Test
    public void t4_findAll() throws Exception {
        signIn();
        @Nullable final List<ProjectDto> list = projectService.findAllProjects(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
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
