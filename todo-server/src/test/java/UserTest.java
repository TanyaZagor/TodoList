import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class UserTest {

    @Inject
    private IUserService userService;

    private String userId = "e7e8f51a-5ea8-464b-a0cb-2f903e120b10";

    @Test
    public void signUp() throws Exception {
        User user = userService.signUp("test", "test", "fn", "ln", "email");
        if (user != null) {
            System.out.println(user.getLogin());
        }
    }

    @Test
    public void signIn() throws Exception {
        User user = userService.signIn("test", "test");
        if (user != null) {
            System.out.println(user.getLogin());
        }
    }

    @Test
    public void changePassword() throws Exception {
        userService.changePassword(userId, "test", "test", "admin");
    }

    @Test
    public void updateUser() throws Exception {
        userService.updateUser(userId, "FN", "LN", "EMAIL");
    }

    @Test
    public void findOne() {
        User user = userService.findOne(userId);
        if (user != null) {
            System.out.println(user.getLogin());
        }
    }

    @Test
    public void remove() throws Exception {
        userService.removeUser(userId);
    }

}
