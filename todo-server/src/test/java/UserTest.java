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
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Autowired
    private IUserService userService;

    private String userId = null;

    @Test
    public void t1_signUp() throws Exception {
        User user = userService.signUp("test", "test", "fn", "ln", "email");
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getLogin());
    }

    @Test
    public void t2_signIn() throws Exception {
        if (userId == null) {
            User user = userService.signIn("test", "test");
            Assert.assertNotNull(user);
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
        Assert.assertNotNull(user);
        Assert.assertEquals("FN", user.getFirstName());
    }

    @Test
    public void t5_findOne() throws Exception {
        t2_signIn();
        @Nullable final User user = userService.findOne(userId);
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getLogin());
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
