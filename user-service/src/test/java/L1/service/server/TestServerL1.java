package service.server;

import io.grpc.testing.StreamRecorder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;
import service.protos.AllUsers;
import service.protos.LoginResponse;
import service.protos.LoginUser;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserServiceConfiguration.class)
@ActiveProfiles("L1")
public class TestServerL1 {

    static PostgreSQLContainer<?> postgres;

    @Autowired
    private UserService userService;

    @BeforeClass
    public static void beforeAll() {
        System.out.println("Opening db");
         postgres = new PostgreSQLContainer<>("postgres:14-alpine");
        postgres.start();
    }
//
    @AfterClass
    public static void afterAll() {
        System.out.println("Closing db");
        postgres.stop();
    }

    @Before
    public void setUp() {
        DBConnectionProvider connectionProvider = new DBConnectionProvider(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );
        userService.getMyUserDAL().setConnectionProvider(connectionProvider);
    }

    @After
    public void teardown() {
        userService.getMyUserDAL().dropUsers();
    }

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

    @Test
    public void testCreateUser() throws Exception {
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

        //get all users
        StreamRecorder<AllUsers> responseObserver2 = StreamRecorder.create();
        userService.getAllUsers(request, responseObserver2);
        if (!responseObserver2.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver2.getError());
        List<AllUsers> results2 = responseObserver2.getValues();
        System.out.println("All users: " + results2.get(0).toString());
        assertEquals(1, results2.size());
        assertEquals(1, results2.get(0).getUserCount());
    }
}