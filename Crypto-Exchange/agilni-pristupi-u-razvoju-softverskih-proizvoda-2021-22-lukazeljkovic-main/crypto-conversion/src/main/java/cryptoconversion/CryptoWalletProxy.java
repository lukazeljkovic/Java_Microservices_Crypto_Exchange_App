package cryptoconversion;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "crypto-wallet")
public interface CryptoWalletProxy {
	
	@GetMapping("/crypto-wallet/{email}")
	CryptoWalletDTO getWallet(@PathVariable String email);

	@PutMapping("/crypto-wallet/{email}/from/{from}/to/{to}/quantity/{quantity}/total/{total}")
	CryptoWalletDTO exchange(@PathVariable String email, @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity, @PathVariable BigDecimal total);
}
