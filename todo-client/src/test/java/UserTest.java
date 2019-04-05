import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.endpoint.UserDto;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class UserTest {

    @Inject
    private UserEndpoint userService;

    @Inject
    private SessionEndpoint sessionService;

    private Session session;

    public void signIn() throws Exception {
        if (session == null) {
            Session session = sessionService.signIn("test", "test");
            if (session != null) {
                this.session = session;
            }
        }
    }

    @Ignore
    @Test
    public void changePassword() throws Exception {
        signIn();
        userService.changePassword(session, "test", "test", "admin");
    }

    @Test
    public void updateUser() throws Exception {
        signIn();
        userService.updateUser(session, "FN", "LN", "EMAIL");
    }

    @Test
    public void findOne() throws Exception {
        signIn();
        UserDto user = userService.findUser(session);
    }

    @Test
    public void remove() throws Exception {
        signIn();
        userService.removeUser(session);
    }

}
