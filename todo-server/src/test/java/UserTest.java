import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Inject
    private IUserService userService;

    private String userId = null;

    @Test
    public void t1_signUp() throws Exception {
        User user = userService.signUp("test", "test", "fn", "ln", "email");
    }

    @Test
    public void t2_signIn() throws Exception {
        if (userId == null) {
            User user = userService.signIn("test", "test");
            userId = user.getId();
        }

    }

    @Ignore
    @Test
    public void t3_changePassword() throws Exception {
        t2_signIn();
        userService.changePassword(userId, "test", "test", "admin");
    }

    @Test
    public void t4_updateUser() throws Exception {
        t2_signIn();
        userService.updateUser(userId, "FN", "LN", "EMAIL");
        @Nullable final User user = userService.findOne(userId);
        if (user != null) {
            Assert.assertEquals("FN", user.getFirstName());
        }
    }

    @Test
    public void t5_findOne() throws Exception {
        t2_signIn();
        @Nullable final User user = userService.findOne(userId);
        if (user != null) {
            Assert.assertEquals("test", user.getLogin());
        }
    }

    @Ignore
    @Test
    public void t6_remove() throws Exception {
        t2_signIn();
        userService.removeUser(userId);
        @Nullable final User user = userService.findOne(userId);
        Assert.assertNull(user);
    }

}
