package esgi.al.cleancode.project.Super_Cards.bootstrap;

import esgi.al.cleancode.project.Super_Cards.bootstrap.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = "esgi.al.cleancode.project.Super_Cards")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
