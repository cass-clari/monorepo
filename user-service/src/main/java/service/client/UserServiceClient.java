package service.client;

import io.grpc.*;
import service.protos.UserServiceGrpc;
import service.protos.LoginResponse;
import service.protos.LoginUser;

import java.util.concurrent.TimeUnit;

public class UserServiceClient {
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    /** Construct client for accessing HelloWorld server using the existing channel. */
    public UserServiceClient(Channel channel) {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
        // shut it down.

        // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public UserServiceClient() {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
        // shut it down.

        String target = "localhost:8080";

        // Create a communication channel to the server, known as a Channel. Channels are thread-safe
        // and reusable. It is common to create channels at the beginning of your application and reuse
        // them until the application shuts down.
        //
        // For the example we use plaintext insecure credentials to avoid needing TLS certificates. To
        // use TLS, use TlsChannelCredentials instead.
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }


    /** Say hello to server. */
    public LoginResponse login(String name, String password) {
        LoginUser request = LoginUser.newBuilder().setUsername(name).setPwd(password).build();
        LoginResponse response;
        try {
            response = blockingStub.login(request);
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: {0}" + e.getStatus());
            return null;
        }
        System.out.println("Greeting: " + response.getMessage());
        return response;
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting. The second argument is the target server.
     */
    public static void main(String[] args) throws Exception {
        String user = "world";
        // Access a service running on the local machine on port 50051

        // Allow passing in the user and target strings as command line arguments
        if (args.length > 0) {
            if ("--help".equals(args[0])) {
                System.err.println("Usage: [name [target]]");
                System.err.println("");
                System.err.println("  name    The name you wish to be greeted by. Defaults to " + user);
                System.err.println("  target  The server to connect to. Defaults to " + "localhost:8080");
                System.exit(1);
            }
            user = args[0];
        }

        UserServiceClient client = new UserServiceClient();
        LoginResponse response = client.login("cass", "cass");
        System.out.println("Service responded with: " + response.toString());
    }
}
