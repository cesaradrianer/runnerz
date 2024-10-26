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
import github.cesaradrianer.io.runnerz.run.RunRepository;

@SpringBootApplication
public class RunnerzApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
	}
	
	/*@Bean
	CommandLineRunner runner(RunRepository runRepository) {
		return args -> {
			Run run = new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plus(6, ChronoUnit.HOURS), 10, Location.OUTDOORS);
			runRepository.create(run);
		};
	}*/

}
