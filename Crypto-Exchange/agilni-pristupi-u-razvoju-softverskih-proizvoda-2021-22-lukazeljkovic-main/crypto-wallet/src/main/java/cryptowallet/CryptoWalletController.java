package cryptowallet;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoWalletController {
	
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	
	
	
	@GetMapping("/crypto-wallet/{email}")
	public CryptoWallet getWallet(@PathVariable String email) {
		return cryptoWalletRepository.findByEmailAddress(email);
	}
	
	@PutMapping("/crypto-wallet/{email}/from/{from}/to/{to}/quantity/{quantity}/total/{total}")
	public CryptoWallet exchange(@PathVariable String email, @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity, @PathVariable BigDecimal total) {
		CryptoWallet wallet = cryptoWalletRepository.findByEmailAddress(email);
		
		switch (from.toUpperCase())
		{
		case "BTC":
			wallet.setBtc(wallet.getBtc().subtract(quantity));
			break;
		case "ETH":
			wallet.setEth(wallet.getEth().subtract(quantity));
			break;
		case "DOGE":
			wallet.setDoge(wallet.getDoge().subtract(quantity));
			break;
		}
		
		switch (to.toUpperCase())
		{
		case "BTC":
			wallet.setBtc(wallet.getBtc().add(total));
			break;
		case "ETH":
			wallet.setEth(wallet.getEth().add(total));
			break;
		case "DOGE":
			wallet.setDoge(wallet.getDoge().add(total));
			break;
		}
		
		
		return cryptoWalletRepository.save(wallet);
	}
	
	@PutMapping("/crypto-wallet/{email}/update/{update}/quantity/{quantity}")
	public CryptoWallet updateOne(@PathVariable String email, @PathVariable String update, @PathVariable BigDecimal quantity) {
		
		CryptoWallet wallet = cryptoWalletRepository.findByEmailAddress(email);
		
		if(update.toUpperCase().equals("BTC")) {
			wallet.setBtc(wallet.getBtc().add(quantity));
		}
		else if(update.toUpperCase().equals("DOGE")) {
			wallet.setDoge(wallet.getDoge().add(quantity));
		}
		else if(update.toUpperCase().equals("ETH")) {
			wallet.setEth(wallet.getEth().add(quantity));
		}
		
		return cryptoWalletRepository.save(wallet);
	}

}
