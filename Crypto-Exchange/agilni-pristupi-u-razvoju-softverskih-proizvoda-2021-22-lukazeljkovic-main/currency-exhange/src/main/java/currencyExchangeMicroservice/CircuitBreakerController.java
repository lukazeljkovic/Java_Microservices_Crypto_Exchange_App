package currencyExchangeMicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/response")
	@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
	@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public String getResponse() {
		return "You can call this only 2 times in 40 seconds";
	}
	
	public String hardCodedResponse(Exception ex) {
		return "Your request was received but there was no response from the dummy microservice";
	} 
}
