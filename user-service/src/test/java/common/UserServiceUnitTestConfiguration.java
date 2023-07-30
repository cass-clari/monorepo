package common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import service.server.UserDAL;
import service.server.UserService;

@Configuration
@Profile({"L0","L2"})
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
