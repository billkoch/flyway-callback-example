package io.billkoch.examples;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.callback.FlywayCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@Profile("seed-data")
	@Configuration
	class Config {

		@Bean
		public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
			flyway.setCallbacks(seedDataFlywayCallback());
			return new FlywayMigrationInitializer(flyway);
		}

		@Bean
		public FlywayCallback seedDataFlywayCallback() {
			return new SeedDataFlywayCallback();
		}
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello, World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
