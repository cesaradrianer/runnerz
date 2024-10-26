package github.cesaradrianer.io.runnerz.run;

import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonDataLoader implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(JsonDataLoader.class);
	private final RunRepository runRepository;
	private final ObjectMapper objMapper;
	
	public JsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
		this.runRepository = runRepository;
		this.objMapper = objectMapper;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if (runRepository.count() == 0) {
			
			try (InputStream iStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
				Runs runList = objMapper.readValue(iStream, Runs.class);
				logger.info("Reading {} runs from JSON data and saving to a database.", runList.runs().size());
				runRepository.saveAll(runList.runs());
			} catch (IOException e) {
				throw new RuntimeException("Failed to read JSON data", e);
			}
			
		} else {
			logger.info("Not loading JSON because the collection contains data!");
		}
		
	}

}
