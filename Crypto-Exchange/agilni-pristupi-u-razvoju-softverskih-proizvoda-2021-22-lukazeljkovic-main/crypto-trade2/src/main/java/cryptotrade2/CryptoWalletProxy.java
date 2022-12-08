package cryptotrade2;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@FeignClient(name = "crypto-wallet")
public interface CryptoWalletProxy {

	@GetMapping("/crypto-wallet/{email}")
	CryptoWalletDTO getWallet(@PathVariable String email);	
	
	@PutMapping("/crypto-wallet/{email}/update/{update}/quantity/{quantity}")
	CryptoWalletDTO updateOne(@PathVariable String email, @PathVariable String update, @PathVariable BigDecimal quantity);
}
