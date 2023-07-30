package service.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import service.client.UserServiceClient;
import service.protos.LoginResponse;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserServiceUnitTestConfiguration.class)
@ActiveProfiles("L2")
public class TestServerL2 {

    private int port = 8080;

    @Autowired
    private UserService userService;

    @Test
    public void testServiceIsIntialized() throws Exception {
        assertNotNull(userService);

        UserServiceServer server = new UserServiceServer();
        Thread t = new Thread(server);
        t.start();

        Thread.sleep(3000);

        UserServiceClient client = new UserServiceClient();
        LoginResponse response = client.login("cass", "cass");
        System.out.println("Service responded with: " + response.toString());
    }
}