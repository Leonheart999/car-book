package ge.levanchitiashvili.carbook;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EntityScan("ge.levanchitiashvili.carbook.models")
@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class})
@EnableJpaRepositories("ge.levanchitiashvili.carbook.repositories.jpa")
public class CarBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarBookApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm= new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mm;
    }

}
