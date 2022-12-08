package cryptoconversion;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CryptoConversionController {

	@Autowired
	public CryptoExchangeProxy proxy;

	@Autowired
	public CryptoWalletProxy cryptoWalletProxy;
	
	
	@GetMapping("/crypto-conversion/from/{from}/to/{to}/quantity/{quantity}/wallet/{email}")
	@RateLimiter(name = "default")
	public ResponseEntity<Object> getConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity, @PathVariable String email) {

		CryptoWalletDTO wallet = cryptoWalletProxy.getWallet(email);
		
		try {
			if(from.toUpperCase().equals("BTC")) {
				if (wallet.getBtc().compareTo(quantity) < 0) {
					throw new Exception("btc");
				}
			}
			else if(from.toUpperCase().equals("ETH")) {
				if (wallet.getEth().compareTo(quantity) < 0) {
					throw new Exception("eth");
				}
			}
			else if(from.toUpperCase().equals("DOGE")) {
				if (wallet.getDoge().compareTo(quantity) < 0) {
					throw new Exception("doge");
				}
			}
		}
		catch (Exception e) {
			return ResponseEntity.ok("NOT ENOUGH " + e.getMessage());
		}

		CryptoConversion temp = proxy.getExchange(from, to);
		
		CryptoWalletDTO walletExchanged = cryptoWalletProxy.exchange(wallet.getEmailAddress(), from, to, quantity, quantity.multiply(temp.getMultiple()));

		return ResponseEntity.ok(walletExchanged); 
				
	}
	
}
