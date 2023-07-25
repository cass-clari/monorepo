package service.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceUnitTestConfiguration {

    @Bean
    UserService myService() {
        return new UserService();
    }

    //I was here

}
