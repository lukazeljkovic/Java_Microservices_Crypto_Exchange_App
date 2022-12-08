package cryptotrade2;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crypto_to_fiat_exchanges")
public class CryptoTrade {

	@Id
	private Long id;
	
	@Column
	private String exchangeFrom;
	
	@Column 
	private String exchangeTo;
	
	@Column(precision = 20, scale = 5)
	private BigDecimal multiplier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExchangeFrom() {
		return exchangeFrom;
	}

	public void setExchangeFrom(String exchangeFrom) {
		this.exchangeFrom = exchangeFrom;
	}

	public String getExchangeTo() {
		return exchangeTo;
	}

	public void setExchangeTo(String exchangeTo) {
		this.exchangeTo = exchangeTo;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

	public CryptoTrade(Long id, String exchangeFrom, String exchangeTo, BigDecimal multiplier) {
		super();
		this.id = id;
		this.exchangeFrom = exchangeFrom;
		this.exchangeTo = exchangeTo;
		this.multiplier = multiplier;
	}

	public CryptoTrade() {
		super();
	}
	
}
