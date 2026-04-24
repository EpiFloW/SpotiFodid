package fodid.SpotiFodid_back.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({ "fodid.SpotiFodid_back.usercontext.repository", "fodid.SpotiFodid_back.catalogcontext.domain.repository" })
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration {

}
