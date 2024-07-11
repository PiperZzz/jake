package spring.code.jake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import spring.code.jake.mythreading.MyThread;

@SpringBootApplication
@EnableCaching
@Profile("${spring.profiles.active}")
@PropertySource("classpath:application.properties")
public class JakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JakeApplication.class, args);
		
		/*  */
		System.out.println(System.getProperty("java.version"));
		try {
			MyThread.completableFutureSupply("From Main Thread");
		} catch (Exception e) {
			
		}
	}

}
