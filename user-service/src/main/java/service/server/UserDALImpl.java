package service.server;

import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;

public class UserDALImpl implements UserDAL {

    public UserDALImpl() {
        System.out.println("UserDALImpl instance created");
    }

    @Override
    public UserOuterClass.User validateUser(String username, String password) {
        System.out.println("Inside real IMPL");
        UserOuterClass.User.Builder u = UserOuterClass.User.newBuilder();
        u.setFirstName(username).setLastName(username+"last");
        EmailAddressOuterClass.EmailAddress.Builder email = EmailAddressOuterClass.EmailAddress.newBuilder();
        email.setEmail(username + "@" + u.getLastName() + ".net");
        u.setEmailAddress(email);

        return u.build();
    }
}
