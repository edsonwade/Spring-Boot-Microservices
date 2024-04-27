package code.with.vanilson;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Profile;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@Profile("dev")
@EnableConfigServer
public class ConfigServiceApplication {
    public static void main(String[] args) {
        run(ConfigServiceApplication.class, args);
    }
}