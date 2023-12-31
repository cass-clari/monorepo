package service.server;

import protos.user.EmailAddressOuterClass;
import protos.user.UserOuterClass;
import service.protos.AllUsers;

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

    @Override
    public void dropUsers() {
        try (Connection conn = this.connectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "drop table if exists users"
            );
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public AllUsers getAllUsers(UserOuterClass.User u) {
        List<UserOuterClass.User> users = getAllUsers();
        AllUsers.Builder allUsers = AllUsers.newBuilder();
        allUsers.addAllUser(users);
        return allUsers.build();
    }

    public void createUser(UserOuterClass.User user) {
        System.out.println("I am creating a user: " + user.getFirstName());
        try (Connection conn = this.connectionProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into users(username,password,firstname,lastname,email_address) values(?,?,?,?,?)"
            );
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getEmailAddress().getEmail());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserOuterClass.User> getAllUsers() {
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
                users.add(
                        UserOuterClass.User.newBuilder()
                                .setFirstName(firstname)
                                .setLastName(lastname)
                                .setEmailAddress(EmailAddressOuterClass.EmailAddress.newBuilder().setEmail(email))
                                .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public UserOuterClass.User getUserByUserName(String username) {
        //let's see
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
                                    password varchar not null,
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

        UserOuterClass.User user = getUserByUserName(username);

        if (user == null) {
            UserOuterClass.User.Builder u = UserOuterClass.User.newBuilder();
            u.setFirstName(username).setLastName(username + "last");
            EmailAddressOuterClass.EmailAddress.Builder email = EmailAddressOuterClass.EmailAddress.newBuilder();
            email.setEmail(username + "@" + u.getLastName() + ".net");
            u.setEmailAddress(email);

            createUser(u.build());

            return u.build();
        } else {
            return user;
        }
    }
}
