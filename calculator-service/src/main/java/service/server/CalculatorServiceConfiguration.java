package service.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default","L1","L2","prod"})
public class CalculatorServiceConfiguration {

    @Bean
    public CalculatorDAL calculatorDAL() { return new CalculatorDALImpl(); }

    @Bean
    public CalculatorService calculatorService() { return new CalculatorService(); }

}
