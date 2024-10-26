package github.cesaradrianer.io.runnerz.run;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(RunRepository.class);
	private final JdbcClient client;
	
	public RunRepository(JdbcClient jdbcClient) {
		this.client = jdbcClient;
	}
	
	public List<Run> getAll() {
		return client.sql("SELECT * FROM RUN")
					 .query(Run.class)
					 .list();
	}
		
	public Optional<Run> findById(Integer id) {
		return client.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id")
					 .param(id)
					 .query(Run.class)
					 .optional();
	}
	
	public void create(Run run) {
		var updated = client.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
							.params(List.of(run.id(), 
									   	   run.title(),
									   	   run.startedOn(),
									   	   run.completedOn(),
									   	   run.miles(),
									   	   run.location().toString())).update();
		Assert.state(updated == 1, "Failed to create Run: " + run.title());
	}
	
	public void update(Run run, Integer id) {
		
		var updated = client.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
							.params(List.of(run.id(),
											run.title(),
											run.startedOn(),
											run.completedOn(),
											run.miles(),
											run.location().toString(), id)).update();
		Assert.state(updated == 1, "Failed to update Run: " + run.title());
		
	}
	
	public void delete(Integer id) {
		var updated = client.sql("DELETE FROM Run WHERE id = :id").param(id, id).update();
		Assert.state(id == 1, "Failed to delete Run: " + id);
	}
	
	public Integer count() {
		return client.sql("SELECT * FROM RUN").query().listOfRows().size();
	}
	
	public void saveAll(List<Run> runList) {
		runList.stream().forEach(this::create);
	}
	
	/*@PostConstruct
	private void init() {
		runList.add(
				new Run(1, 
						"Monday Run", 
						LocalDateTime.now(), 
						LocalDateTime.now().plusHours(6), 
						10, 
						Location.OUTDOORS)
				);
		runList.add(
				new Run(2, 
						"Saturday Evening Run", 
						LocalDateTime.now(), 
						LocalDateTime.now().plusHours(9), 
						1, 
						Location.OUTDOORS)
				);
	}*/
	
}
