package service.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default","L1","L2","prod"})
public class UserServiceConfiguration {

    @Bean
    public UserDAL myUserDAL() { return new UserDALImpl(); }

    @Bean
    public UserService userService() { return new UserService(); }

}
