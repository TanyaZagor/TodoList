import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.endpoint.UserDto;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;
import ru.zagorodnikova.tm.util.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Autowired
    private UserEndpoint userService;

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

    @Ignore
    @Test
    public void changePassword() throws Exception {
        signIn();
        userService.changePassword(session, "test", "test", "admin");
        @Nullable final Session testSession = sessionService.signIn("test", "test");
        Assert.assertNull(testSession);
        sessionService.remove(session);
    }

    @Test
    public void t1_updateUser() throws Exception {
        signIn();
        userService.updateUser(session, "FN", "LN", "EMAIL");
        @Nullable final UserDto user = userService.findUser(session);
        Assert.assertNotNull(user);
        Assert.assertEquals("FN", user.getFirstName());
        sessionService.remove(session);
    }

    @Test
    public void t2_findOne() throws Exception {
        signIn();
        @Nullable final UserDto user = userService.findUser(session);
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getLogin());
        sessionService.remove(session);
    }

    @Ignore
    @Test
    public void t3_remove() throws Exception {
        signIn();
        userService.removeUser(session);
        @Nullable final UserDto user = userService.findUser(session);
        Assert.assertNull(user);
        sessionService.remove(session);
    }

}
