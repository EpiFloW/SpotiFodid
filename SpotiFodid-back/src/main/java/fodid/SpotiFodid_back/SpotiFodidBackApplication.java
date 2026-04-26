package fodid.SpotiFodid_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("fodid.SpotiFodid_back.catalogcontext.domain")
@EnableJpaRepositories("fodid.SpotiFodid_back.catalogcontext.repository")
public class SpotiFodidBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotiFodidBackApplication.class, args);
    }
}
