package fodid.SpotiFodid_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "fodid.SpotiFodid_back", 
    "usercontext" 
})
@EnableJpaRepositories(basePackages = {
    "fodid.SpotiFodid_back", 
    "usercontext"
})
@EntityScan(basePackages = {
    "fodid.SpotiFodid_back", 
    "usercontext"
})
public class SpotiFodidBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotiFodidBackApplication.class, args);
    }
}
