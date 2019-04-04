import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class SessionTest {

    @Inject
    private SessionEndpoint sessionService;

//    @Ignore
//    @Test
//    public void signUp() throws Exception_Exception {
//        Session session = sessionService.signUp("test", "test", "fn", "ln", "email");
//        if (session != null) {
//            System.out.println(session.getUserId());
//        }
//    }

    @Test
    public void signIn() throws Exception_Exception {
        Session session = sessionService.signIn("test", "test");
        if (session != null) {
            System.out.println(session.getUserId());
        }
    }


}
