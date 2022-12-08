package cryptotrade2;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


@RestController
public class CryptoTradeController {

	@Autowired
	CryptoTradeRepository repo;

	@Autowired
	private CryptoWalletProxy walletProxy;

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	@Autowired
	private BankAccountProxy bankProxy;
	
	double addTo;
	double quantityToReduce;
	

	
	@GetMapping("/crypto-trade/from/{from}/to/{to}/quantity/{quantity}/user/{email}")
	@RateLimiter(name = "default")
	public ResponseEntity<Object> trade(@PathVariable String from, @PathVariable String to, @PathVariable String email, @PathVariable BigDecimal quantity) {
		
		if (from.toUpperCase().equals("BTC") || from.toUpperCase().equals("ETH") || from.toUpperCase().equals("DOGE")) {
			CryptoWalletDTO wallet = walletProxy.getWallet(email);
			if(wallet == null) {
				return ResponseEntity.ok("CRYPTO WALLET NOT FOUND");
			}

			try {
				if(from.toUpperCase().equals("BTC")) {
					if (wallet.getBtc().compareTo(quantity) < 0) {
						throw new RuntimeException("BTC");
					}
				}
				else if(from.toUpperCase().equals("ETH")) {
					if (wallet.getEth().compareTo(quantity) < 0) {
						throw new RuntimeException("ETH");
					}
				}
				else if(from.toUpperCase().equals("DOGE")) {
					if (wallet.getAda().compareTo(quantity) < 0) {
						throw new RuntimeException("DOGE");
					}
				}
				else {
					return ResponseEntity.ok("UNSUPORTED CRYPTO CURRENCY");
				}
					
			} catch (Exception e) {
				return ResponseEntity.ok("NOT ENOUGH " + e.getMessage());
			}

			quantityToReduce = 0 - quantity.doubleValue();

			if (to.toUpperCase().equals("EUR") || to.toUpperCase().equals("USD")) {
				CryptoTrade ct = repo.findByExchangeFromAndExchangeTo(from, to);

				addTo =  quantity.multiply(ct.getMultiplier()).doubleValue();
			} else if (to.toUpperCase().equals("CHF") || to.toUpperCase().equals("GBP")
					|| to.toUpperCase().equals("RSD")) {

				CryptoTrade ct = repo.findByExchangeFromAndExchangeTo(from, "USD");

				CurrencyExchangeDTO ce = currencyExchangeProxy.getExchange("USD", to.toUpperCase());

				addTo = quantity.multiply(ct.getMultiplier()).multiply(ce.getConversionMultiple()).doubleValue();
			}

			walletProxy.updateOne(email, from, new BigDecimal(addTo).setScale(5, RoundingMode.HALF_UP));

			BankAccountDTO bankAccount = bankProxy.updateOne(email, to, new BigDecimal(addTo).setScale(5, RoundingMode.HALF_UP));

			return ResponseEntity.ok(bankAccount);
		}
		else if (from.toUpperCase().equals("EUR") || from.toUpperCase().equals("USD")) {
			BankAccountDTO bankAccount = bankProxy.getBankAccount(email);
			if(bankAccount == null) {
				return ResponseEntity.ok("BANK ACCOUNT NOT FOUND");
			}

			try {
				if(from.toUpperCase().equals("EUR")) {
					if (bankAccount.getEur().compareTo(quantity) < 0) {
						throw new RuntimeException("EUR");
					}
				}
				else if(from.toUpperCase().equals("USD")) {
					if (bankAccount.getUsd().compareTo(quantity) < 0) {
						throw new RuntimeException("USD");
					}
				}
			} catch (Exception e) {
				return ResponseEntity.ok("NOT ENOUGH " + e.getMessage());
			}

			quantityToReduce = 0 - quantity.doubleValue();

			CryptoTrade cryptoTrade = repo.findByExchangeFromAndExchangeTo(from, to);

			addTo = quantity.multiply(cryptoTrade.getMultiplier()).doubleValue();

			CryptoWalletDTO wallet = walletProxy.updateOne(email, to, new BigDecimal(addTo));

			bankProxy.updateOne(email, from, new BigDecimal(quantityToReduce));

			return ResponseEntity.ok(wallet);
		}
		else if (from.toUpperCase().equals("CHF") || from.toUpperCase().equals("GBP")
				|| from.toUpperCase().equals("RSD")) {
			BankAccountDTO bankAccount = bankProxy.getBankAccount(email);
			if(bankAccount == null) {
				return ResponseEntity.ok("BANK ACCOUNT NOT FOUND");
			}
			
			try {
				if(from.toUpperCase().equals("RSD")) {
					if (bankAccount.getEur().compareTo(quantity) < 0) {
						throw new RuntimeException("RSD");
					}
				}
				else if(from.toUpperCase().equals("CHF")) {
					if (bankAccount.getUsd().compareTo(quantity) < 0) {
						throw new RuntimeException("CHF");
					}
				}
				else if(from.toUpperCase().equals("GBP")) {
					if (bankAccount.getUsd().compareTo(quantity) < 0) {
						throw new RuntimeException("GBP");
					}
				}
			} catch (Exception e) {
				return ResponseEntity.ok("NOT ENOUGH " + e.getMessage());
			}
			
			quantityToReduce = 0 - quantity.doubleValue();
			
			CryptoTrade cryptoTrade = repo.findByExchangeFromAndExchangeTo("USD", to.toUpperCase());

			CurrencyExchangeDTO ce = currencyExchangeProxy.getExchange(from.toUpperCase(), "USD");
			
			addTo = quantity.multiply(cryptoTrade.getMultiplier()).multiply(ce.getConversionMultiple()).doubleValue();
			
			CryptoWalletDTO wallet = walletProxy.updateOne(email, to, new BigDecimal(addTo));

			bankProxy.updateOne(email, from, new BigDecimal(quantityToReduce));

			return ResponseEntity.ok(wallet);
		}
		
		return null;
	}
	
}
