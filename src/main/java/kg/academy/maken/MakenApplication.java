package kg.academy.maken;

import kg.academy.maken.service.UserService;
import kg.academy.maken.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakenApplication.class, args);
	}

}
