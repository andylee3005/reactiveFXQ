package lee.andyfxq;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import lee.andyfxq.model.FXQFactory;
import lee.andyfxq.model.FXQuote;
import lee.andyfxq.repository.FXQRepository;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveFxqApplication implements CommandLineRunner {
	
	@Autowired
	private FXQRepository fxqRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ReactiveFxqApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		FXQFactory fxqFactory = new FXQFactory();
		while (true) {
			List<FXQuote> quotes = fxqFactory.generate((int) (Math.random() * 10) + 1);
			fxqRepo.saveAll(quotes).subscribe();
			TimeUnit.SECONDS.sleep((int) (Math.random() * 5) + 1);
		}
		
	}

}
