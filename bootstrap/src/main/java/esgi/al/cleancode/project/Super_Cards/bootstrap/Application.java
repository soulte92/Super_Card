package esgi.al.cleancode.project.Super_Cards.bootstrap;

import esgi.al.cleancode.project.Super_Cards.bootstrap.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import({ApplicationConfiguration.class})
@EntityScan("esgi.al.cleancode.project.Super_Cards.server.postgres.entity")
@EnableJpaRepositories(basePackages = "esgi.al.cleancode.project.Super_Cards.server.postgres.repository")
@SpringBootApplication(scanBasePackages = "esgi.al.cleancode.project.Super_Cards")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
