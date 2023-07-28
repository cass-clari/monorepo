package service.server;

import org.junit.Test;
import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;
import service.protos.LoginResponse;
import service.protos.LoginUser;
import io.grpc.testing.StreamRecorder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestServerL0 {

    private UserService myService = new UserService();

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


        EmailAddressOuterClass.EmailAddress.Builder e = EmailAddressOuterClass.EmailAddress.newBuilder();
        e.setEmail("cass@casslast.net");

        UserOuterClass.User.Builder u = UserOuterClass.User.newBuilder();
        u.setFirstName("cass").setLastName("casslast").setEmailAddress(e);

        assertEquals(LoginResponse.newBuilder()
                .setMessage("Hello there!").setUser(u.build())
                .build(), response);
    }

    @Test
    public void simpleTest() {
        assertEquals("cass", "cass");
    }
}