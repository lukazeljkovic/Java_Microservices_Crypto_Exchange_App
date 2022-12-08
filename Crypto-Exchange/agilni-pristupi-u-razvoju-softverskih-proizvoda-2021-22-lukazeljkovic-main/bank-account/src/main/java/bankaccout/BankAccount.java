package bankaccout;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

	@Id
	private long id;
	
	@Column(unique = true,name="email_address")
	private String emailAddress;
	
	@Column(name="rsd", precision = 50, scale=5)
	private BigDecimal rsd;
	
	@Column(name="usd", precision = 50, scale=5)
	private BigDecimal usd;
	
	@Column(name="eur", precision = 50, scale=5)
	private BigDecimal eur;
	
	@Column(name="gbp", precision = 50, scale=5)
	private BigDecimal gbp;
	
	@Column(name="chf", precision = 50, scale=5)
	private BigDecimal chf;

	public BankAccount()
	{
		
	}
	
	public BankAccount(Long id, String emailAddress, BigDecimal usd, BigDecimal gbp, BigDecimal chf, BigDecimal eur, BigDecimal rsd) {
		this.id = id;
		this.emailAddress = emailAddress;
		this.usd = usd;
		this.gbp = gbp;
		this.chf = chf;
		this.eur = eur;
		this.rsd = rsd;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public BigDecimal getUsd() {
		return usd;
	}

	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	}

	public BigDecimal getGbp() {
		return gbp;
	}

	public void setGbp(BigDecimal gbp) {
		this.gbp = gbp;
	}

	public BigDecimal getChf() {
		return chf;
	}

	public void setChf(BigDecimal chf) {
		this.chf = chf;
	}

	public BigDecimal getEur() {
		return eur;
	}

	public void setEur(BigDecimal eur) {
		this.eur = eur;
	}

	public BigDecimal getRsd() {
		return rsd;
	}

	public void setRsd(BigDecimal rsd) {
		this.rsd = rsd;
	}

	
}
