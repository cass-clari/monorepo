package service.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserDAL myUserDAL() { return new UserDALImpl(); }

    @Bean
    public UserService userService() { return new UserService(); }

}
