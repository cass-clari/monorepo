package common;

import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;
import service.protos.AllUsers;
import service.server.DBConnectionProvider;
import service.server.UserDAL;

public class UserDALStub implements UserDAL {
    @Override
    public UserOuterClass.User validateUser(String username, String password) {
        System.out.println("Inside STUB IMPL");
        UserOuterClass.User.Builder u = UserOuterClass.User.newBuilder();
        u.setFirstName(username).setLastName(username + "last");
        EmailAddressOuterClass.EmailAddress.Builder email = EmailAddressOuterClass.EmailAddress.newBuilder();
        email.setEmail(username + "@" + u.getLastName() + ".net");
        u.setEmailAddress(email);

        return u.build();
    }

    public void setConnectionProvider(DBConnectionProvider connectionProvider) {

    }

    @Override
    public void dropUsers() {

    }

    @Override
    public AllUsers getAllUsers(UserOuterClass.User u) {
        return null;
    }
}
