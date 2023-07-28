package service.server;

import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDALImpl implements UserDAL {

    private DBConnectionProvider connectionProvider;

    public UserDALImpl() {
        System.out.println("UserDALImpl instance created");
    }

    public void setConnectionProvider(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        createUsersTableIfNotExists();
    }

    public void createUser(UserOuterClass.User user) {
        try (Connection conn = this.connectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into users(username,password,firstname,lastname,email_address) values(?,?,?,?,?)"
            );
            pstmt.setString(1, user.getFirstName() + "_" + user.getLastName());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getEmailAddress().getEmail());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserOuterClass.User> getAllCustomers() {
        List<UserOuterClass.User> users = new ArrayList<>();

        try (Connection conn = this.connectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("select username,password,firstname,lastname,email_address from users");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String pwd = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email_address");
                users.add(UserOuterClass.User.newBuilder().setFirstName(firstname).setLastName(lastname).build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public UserOuterClass.User getUserByUserName(String username) {

        UserOuterClass.User user = null;

        try (Connection conn = this.connectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("select username,password,firstname,lastname,email_address from users where username = ?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String pwd = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email_address");
                user = UserOuterClass.User.newBuilder().setFirstName(firstname).setLastName(lastname).build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private void createUsersTableIfNotExists() {
        try (Connection conn = this.connectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    """
                                create table if not exists users (
                                    id serial not null,
                                    username varchar not null,
                                    pwd varchar not null,
                                    firstname varchar not null,
                                    lastname varchar not null,
                                    email_address varchar not null,
                                    primary key (id)
                                )
                                """
            );
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
