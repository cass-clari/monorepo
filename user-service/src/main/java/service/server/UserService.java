package service.server;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import protos.user.UserOuterClass.User;
import service.protos.AllUsers;
import service.protos.LoginResponse;
import service.protos.LoginUser;
import service.protos.UserServiceGrpc;

public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserDAL myUserDAL;

    @Override
    public void login(LoginUser req, StreamObserver<LoginResponse> responseObserver) {
        System.out.println("Received request and I like it:: " + req.toString());
        User u = myUserDAL.validateUser(req.getUsername(), req.getPwd());
        LoginResponse reply = LoginResponse.newBuilder().setMessage("Hello there!").setStatus(200).setUser(u).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllUsers(LoginUser req, StreamObserver<AllUsers> responseObserver) {
        System.out.println("Received request:: " + req.toString());
        System.out.println("Validating user:: " + req.toString());
        User u = myUserDAL.validateUser(req.getUsername(), req.getPwd());
        AllUsers reply = myUserDAL.getAllUsers(u);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    public UserDAL getMyUserDAL() {
        return myUserDAL;
    }
}