package cryptotrade2;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CryptoTradeRepository extends JpaRepository<CryptoTrade, Long> {

	CryptoTrade findByExchangeFromAndExchangeTo(String from, String to);
}
