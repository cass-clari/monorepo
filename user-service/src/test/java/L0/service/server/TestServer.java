package service.server;

import common.UserServiceUnitTestConfiguration;
import io.grpc.testing.StreamRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;
import service.protos.LoginResponse;
import service.protos.LoginUser;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserServiceUnitTestConfiguration.class)
public class TestServer {

    @Autowired
    private UserService userService;

    @Test
    public void testLoginResponse() throws Exception {
        LoginUser request = LoginUser.newBuilder()
                .setUsername("cass")
                .setPwd("cass")
                .build();
        StreamRecorder<LoginResponse> responseObserver = StreamRecorder.create();
        userService.login(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<LoginResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        LoginResponse response = results.get(0);


        EmailAddressOuterClass.EmailAddress.Builder e = EmailAddressOuterClass.EmailAddress.newBuilder();
        e.setEmail("cass@casslast.net");

        UserOuterClass.User.Builder u = UserOuterClass.User.newBuilder();
        u.setFirstName("cass").setLastName("casslast").setEmailAddress(e);

        assertEquals(LoginResponse.newBuilder()
                .setMessage("Hello there!").setUser(u.build()).setStatus(200)
                .build(), response);
    }
}