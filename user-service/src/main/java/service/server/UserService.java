package service.server;

import com.google.protobuf.Message;
import io.grpc.stub.StreamObserver;
import protos.user.EmailAddressOuterClass.EmailAddress;
import protos.user.UserOuterClass.User;
import service.protos.LoginResponse;
import service.protos.LoginUser;
import service.protos.UserServiceGrpc;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 8080;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new UserService())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    UserService.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final UserService server = new UserService();
        server.start();
        server.blockUntilShutdown();
    }

    @Override
    public void login(LoginUser req, StreamObserver<LoginResponse> responseObserver) {
        System.out.println("Received request: " + req.toString());
        User u = verifyUser(req.getUsername(), req.getPwd());
        LoginResponse reply = LoginResponse.newBuilder().setMessage("Hello there!").setUser(u).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private User verifyUser(String username, String password) {
        User.Builder u = User.newBuilder();
        u.setFirstName(username).setLastName(username+"last");
        EmailAddress.Builder email = EmailAddress.newBuilder();
        email.setEmail(username + "@" + u.getLastName() + ".net");
        u.setEmailAddress(email);

        return u.build();
    }
}