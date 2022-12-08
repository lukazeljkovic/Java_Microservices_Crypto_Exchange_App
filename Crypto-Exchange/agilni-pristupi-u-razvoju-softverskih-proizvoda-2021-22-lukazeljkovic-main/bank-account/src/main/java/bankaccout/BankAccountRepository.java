package bankaccout;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	BankAccount findByEmailAddress(String email);
}

