package currencyConversion;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CurrencyConversionController {

	@Autowired
	public CurrencyExchangeProxy currencyExchangeProxy;
	
	@Autowired
	public BankAccountProxy bankProxy;


	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}/user/{email}")
	@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
	@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public ResponseEntity<Object> getConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity, @PathVariable String email) {

		BankAccountDTO bankAccount = bankProxy.getBankAccount(email);
		if(bankAccount == null) {
			return ResponseEntity.ok("BANK ACCOUNT NOT FOUND.");
		}
		
		try {
			
			if(from.toUpperCase().equals("USD")) {
				if (bankAccount.getUsd().compareTo(quantity) < 0) {
					throw new Exception("USD");
				}
			}
			else if(from.toUpperCase().equals("GBP")) {
				if (bankAccount.getGbp().compareTo(quantity) < 0) {
					throw new Exception("GBP");
				}
			}
			else if(from.toUpperCase().equals("CHF")) {
				if (bankAccount.getChf().compareTo(quantity) < 0) {
					throw new Exception("CHF");
				}
			}
			else if(from.toUpperCase().equals("EUR")) {
				if (bankAccount.getEur().compareTo(quantity) < 0) {
					throw new Exception("EUR");
				}
			}
			else if(from.toUpperCase().equals("RSD")) {
				if (bankAccount.getRsd().compareTo(quantity) < 0) {
					throw new Exception("RSD");
				}
			}
		}
		catch (Exception e) {
			return ResponseEntity.ok("not enough of " + e.getMessage());
		}

		CurrencyConversion conv = currencyExchangeProxy.getExchange(from, to);
		
		return ResponseEntity.ok(bankProxy.exchangeCurrency(bankAccount.getEmailAddress(), from, to, quantity, quantity.multiply(conv.getConversionMultiple())));
	}
	
	public ResponseEntity<Object> hardCodedResponse(Exception ex) {
		return ResponseEntity.ok("You can send only 2 requests in 60 seconds!");
	}
	
}
