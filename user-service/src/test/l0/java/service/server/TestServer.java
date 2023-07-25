package service.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import service.protos.LoginResponse;
import service.protos.LoginUser;
import io.grpc.testing.StreamRecorder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@Import(UserServiceUnitTestConfiguration.class)
@ContextConfiguration(classes = UserServiceUnitTestConfiguration.class)
public class TestServer {

    @Autowired
    private UserService myService;

    @Test
    public void testLoginResponse() throws Exception {
        LoginUser request = LoginUser.newBuilder()
                .setUsername("cass")
                .setPwd("cass")
                .build();
        StreamRecorder<LoginResponse> responseObserver = StreamRecorder.create();
        myService.login(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<LoginResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        LoginResponse response = results.get(0);
        assertEquals(LoginResponse.newBuilder()
                .setMessage("Hello there!")
                .build(), response);
    }

    @Test
    public void simpleTest() {
        assertEquals("cass", "cass");
    }
}