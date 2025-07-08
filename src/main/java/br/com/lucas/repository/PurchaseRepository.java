package br.com.lucas.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	List<Purchase> findByClientId(Long clientId);
	
//	Buscar acima de um valor
	List<Purchase> findByValueGreaterThan(BigDecimal value);
}
