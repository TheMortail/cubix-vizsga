package hu.cubix.spring.gyuri.logistics;

import hu.cubix.spring.gyuri.logistics.repository.TPUserRepository;
import hu.cubix.spring.gyuri.logistics.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner {
	@Autowired
	private InitDbService initDbService;
	@Autowired
	TPUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.createUserIfNeeded();
		initDbService.resetDbWithDummyData();

		userRepository.findAll().forEach(user->{
			System.out.println("name:" + user.getUsername());
			System.out.println("roles" + user.getRoles());
			System.out.println();
		});

	}
}
