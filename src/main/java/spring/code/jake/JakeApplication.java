package spring.code.jake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import spring.code.jake.mythreading.MyThread;

@SpringBootApplication
@EnableCaching
public class JakeApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JakeApplication.class, args);
		System.out.println(System.getProperty("java.version"));

		MyThread.completableFutureSupply("From Main Thread");
	}

}
