package fodid.SpotiFodid_back;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import usercontext.repository.UserRepository;

@SpringBootApplication(scanBasePackages = {"fodid.SpotiFodid_back", "usercontext"})
@EnableJpaRepositories(basePackages = "usercontext.repository")
@EntityScan(basePackages = "usercontext.domain")
public class SpotiFodidBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotiFodidBackApplication.class, args);
    }

    // AJOUTE CE BLOC POUR TESTER AU DÉMARRAGE
    @Bean
    CommandLineRunner testDatabase(UserRepository userRepository) {
        return args -> {
            System.out.println("--- TEST CONNEXION BDD ---");
            try {
                long count = userRepository.count();
                System.out.println("Nombre d'users en base : " + count);
            } catch (Exception e) {
                System.err.println("ERREUR BDD : " + e.getMessage());
            }
        };
    }
}
