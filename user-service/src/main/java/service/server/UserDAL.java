package service.server;

import protos.user.UserOuterClass;
import service.protos.AllUsers;

public interface UserDAL {

    public UserOuterClass.User validateUser(String username, String password);

    public void setConnectionProvider(DBConnectionProvider connectionProvider);

    public void dropUsers();

    public AllUsers getAllUsers(UserOuterClass.User u);
}
