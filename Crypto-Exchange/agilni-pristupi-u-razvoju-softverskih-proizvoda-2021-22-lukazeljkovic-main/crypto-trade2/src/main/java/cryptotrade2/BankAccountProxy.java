package cryptotrade2;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bank-account")
public interface BankAccountProxy {

	@GetMapping("/bank-account/user/{email}")
	BankAccountDTO getBankAccount(@PathVariable String email);

	@PutMapping("/bank-account/{email}/update/{update}/quantity/{quantity}")
	BankAccountDTO updateOne(@PathVariable String email, @PathVariable String update, @PathVariable BigDecimal quantity);
}
