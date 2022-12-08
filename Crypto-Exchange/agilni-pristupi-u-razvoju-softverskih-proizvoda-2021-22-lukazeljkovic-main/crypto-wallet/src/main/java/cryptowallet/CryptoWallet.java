package cryptowallet;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crypto_wallets")
public class CryptoWallet {

	@Id
	private Long id;

	@Column(precision = 20, scale = 5)
	private BigDecimal BTC;

	@Column(precision = 20, scale = 5)
	private BigDecimal ETH;

	@Column(precision = 20, scale = 5)
	private BigDecimal DOGE;

	@Column(name = "email_address", unique = true)
	
	private String emailAddress;

	public CryptoWallet() {
		super();
	}

	public CryptoWallet(Long id, BigDecimal BTC, BigDecimal ETH, BigDecimal DOGE, String emailAddress) {
		this.id = id;
		this.BTC = BTC;
		this.ETH = ETH;
		this.DOGE = DOGE;
		this.emailAddress = emailAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBtc() {
		return BTC;
	}

	public void setBtc(BigDecimal btc) {
		this.BTC = btc;
	}

	public BigDecimal getEth() {
		return ETH;
	}

	public void setEth(BigDecimal eth) {
		this.ETH = eth;
	}

	public BigDecimal getDoge() {
		return DOGE;
	}

	public void setDoge(BigDecimal doge) {
		this.DOGE = doge;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
