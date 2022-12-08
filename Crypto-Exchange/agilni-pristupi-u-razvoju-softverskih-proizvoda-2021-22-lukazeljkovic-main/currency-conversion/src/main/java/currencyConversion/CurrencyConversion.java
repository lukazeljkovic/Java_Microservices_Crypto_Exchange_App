package currencyConversion;

import java.math.BigDecimal;

public class CurrencyConversion {

	private long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private BigDecimal quantity;
	private BigDecimal totalConversionAmount;
	private String environment;

	public CurrencyConversion() {
		super();
	}

	public CurrencyConversion(long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal totalConversionAmount, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
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

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
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
