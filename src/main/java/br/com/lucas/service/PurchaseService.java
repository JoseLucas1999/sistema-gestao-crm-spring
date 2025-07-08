package br.com.lucas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lucas.model.Purchase;
import br.com.lucas.repository.PurchaseRepository;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

//-------------------------------------------------------------------------    
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

//-------------------------------------------------------------------------    
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

//-------------------------------------------------------------------------    
    public Optional<Purchase> findById(Long id) {
        return purchaseRepository.findById(id);
    }

//-------------------------------------------------------------------------    
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

//-------------------------------------------------------------------------    
    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }

//-------------------------------------------------------------------------    
    public List<Purchase> findByClientId(Long clientId) {
        return purchaseRepository.findByClientId(clientId);
    }
//-------------------------------------------------------------------------    
	public List<Purchase> findByValueGreaterThan(BigDecimal value){
		return purchaseRepository.findByValueGreaterThan(value);
	}
//-------------------------------------------------------------------------    
}
