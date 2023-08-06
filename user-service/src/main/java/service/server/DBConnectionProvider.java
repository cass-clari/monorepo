package service.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionProvider {

    private String url;
    private String username;
    private String password;

    public DBConnectionProvider(String url, String username, String password) {
        System.out.println("Creating DBConnectionProvider with: " + url + " " + username + " " + password);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
