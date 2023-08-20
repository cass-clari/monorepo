package service.server;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CalculatorServiceServer implements Runnable{

    private Server server;

    @Value("${url:defaultUrl}")
    private String url;
    @Value("${username:defaultUsername}")
    private String username;
    @Value("${password:defaultPassword}")
    private String password;

    @Autowired
    private CalculatorService calculatorService;

    private void startServer() throws IOException {
//        DBConnectionProvider connectionProvider = new DBConnectionProvider(
//                url,
//                username,
//                password
//        );
        //calculatorService.getMyUserDAL().setConnectionProvider(connectionProvider);

        /* The port on which the server should run */
        int port = 8081;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(calculatorService)
                .addService(ProtoReflectionService.newInstance())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    CalculatorServiceServer.this.stop();
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

    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(CalculatorServiceServer.class, args);

        System.out.println(context.getEnvironment());

        CalculatorServiceServer server = context.getBean(CalculatorServiceServer.class);
        server.startServer();
        server.blockUntilShutdown();
    }

    @Override
    public void run() {
        try {
            main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
