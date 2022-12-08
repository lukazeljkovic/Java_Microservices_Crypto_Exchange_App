package cryptoconversion;

import java.math.BigDecimal;

public class CryptoWalletDTO {

	private Long id;
	
	private BigDecimal btc;
	
	private BigDecimal eth;  
	
	private BigDecimal doge; 
	
	private Long bankAccountId;
	
	private String emailAddress;

	public Long getId() {
		return id;
	}

	public BigDecimal getBtc() {
		return btc;
	}

	public BigDecimal getEth() {
		return eth;
	}

	public BigDecimal getDoge() {
		return doge;
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	
}
