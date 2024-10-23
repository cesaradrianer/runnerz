package github.cesaradrianer.io.runnerz;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import github.cesaradrianer.io.runnerz.run.Location;
import github.cesaradrianer.io.runnerz.run.Run;

@SpringBootApplication
public class RunnerzApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			
		};
	}

}
