package currencyConversion;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import currencyConversion.BankAccountDTO;

@FeignClient(name = "bank-account")
public interface BankAccountProxy {

	@GetMapping("/bank-account/user/{email}")
	BankAccountDTO getBankAccount(@PathVariable String email);
	
	@PutMapping("/bank-account/{email}/from/{from}/to/{to}/amount/{amount}/total/{total}")
	BankAccountDTO exchangeCurrency(@PathVariable String email, @PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal amount, @PathVariable BigDecimal total);
	
}
