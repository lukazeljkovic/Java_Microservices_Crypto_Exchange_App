package cryptoconversion;

import java.math.BigDecimal;

public class CryptoConversion {

	private long id;
	private String from;
	private String to;
	private BigDecimal multiple;
	private BigDecimal quantity;
	private BigDecimal totalConversionAmount;
	private String environment;

	public CryptoConversion() {
		super();
	}

	public CryptoConversion(long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal totalConversionAmount, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.multiple = conversionMultiple;
		this.quantity = quantity;
		this.totalConversionAmount = totalConversionAmount;
		this.environment = environment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getMultiple() {
		return multiple;
	}

	public void setMultiple(BigDecimal multiple) {
		this.multiple = multiple;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalConversionAmount() {
		return totalConversionAmount;
	}

	public void setTotalConversionAmount(BigDecimal totalConversionAmount) {
		this.totalConversionAmount = totalConversionAmount;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
