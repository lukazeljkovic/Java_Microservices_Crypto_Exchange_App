package cryptoexchange;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "crypto_exchanges")
public class CryptoExchange {
	
	@Id
	private Long id;

	@Column(name = "crypto_from")
	private String from;
	
	@Column(name = "crypto_to")
	private String to;
	
	@Column(precision = 20, scale = 5)
	private BigDecimal multiple;
	
	@Transient
	private String environment;

	public Long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getMultiple() {
		return multiple;
	}

	public String getEnvironment() {
		return environment;
	}
	
	public CryptoExchange() {
		super();
	}

	public CryptoExchange(Long id, String from, String to, BigDecimal multiple, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.multiple = multiple;
		this.environment = environment;
	}
}
