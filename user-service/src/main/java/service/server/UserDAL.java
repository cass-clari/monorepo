package service.server;

import protos.user.UserOuterClass;

public interface UserDAL {

    public UserOuterClass.User validateUser(String username, String password);
}
