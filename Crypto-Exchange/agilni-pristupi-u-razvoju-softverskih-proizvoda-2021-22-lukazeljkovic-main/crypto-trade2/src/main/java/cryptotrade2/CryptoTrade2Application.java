package cryptotrade2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CryptoTrade2Application {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTrade2Application.class, args);
	}

}
