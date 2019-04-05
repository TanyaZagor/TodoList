import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class UserTest {

    @Inject
    private IUserService userService;

    private String userId = null;

    @Test
    public void signUp() throws Exception {
        User user = userService.signUp("test", "test", "fn", "ln", "email");
    }

    @Test
    public void signIn() throws Exception {
        if (userId == null) {
            User user = userService.signIn("test", "test");
            userId = user.getId();
        }

    }

    @Ignore
    @Test
    public void changePassword() throws Exception {
        signIn();
        userService.changePassword(userId, "test", "test", "admin");
    }

    @Test
    public void updateUser() throws Exception {
        signIn();
        userService.updateUser(userId, "FN", "LN", "EMAIL");
    }

    @Test
    public void findOne() throws Exception {
        signIn();
        User user = userService.findOne(userId);
    }

    @Ignore
    @Test
    public void remove() throws Exception {
        signIn();
        userService.removeUser(userId);
    }

}
