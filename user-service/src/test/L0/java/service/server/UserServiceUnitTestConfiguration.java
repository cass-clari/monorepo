package service.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceUnitTestConfiguration {

    @Bean
    UserDAL myUserDAL() {
        return new UserDALStub();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
