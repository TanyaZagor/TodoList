import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionTest {

    @Inject
    private SessionEndpoint sessionService;

    private Session session;

    @Test
    public void signUp() throws Exception {
        Session session = sessionService.signUp("test", "test", "fn", "ln", "email");
        if (session != null) {
            this.session = session;
        }
    }

    @Test
    public void signIn() throws Exception {
        Session session = sessionService.signIn("test", "test");
        if (session != null) {
            this.session = session;
        }
    }

    @Test
    public void remove() throws Exception {
        signIn();
        sessionService.remove(session);
    }

}
