package br.com.lucas.controller;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.model.Purchase;
import br.com.lucas.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
//--------------------------------------------------------------------------
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

//--------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.findAll());
    }

//--------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable Long id) {
        return purchaseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//--------------------------------------------------------------------------
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(purchaseService.findByClientId(clientId));
    }

//--------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<Purchase> create(@RequestBody Purchase purchase) {
        return ResponseEntity.ok(purchaseService.save(purchase));
    }

//--------------------------------------------------------------------------
    @PostMapping("/batch")
    public ResponseEntity<List<Purchase>> createMultiplesPurchases(@RequestBody List<Purchase> purchases) {
    	return ResponseEntity.ok(purchaseService.saveAll(purchases));
    }
    
//--------------------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable Long id, @RequestBody Purchase updated) {
        return purchaseService.findById(id).map(purchase -> {
            purchase.setDate(updated.getDate());
            purchase.setDescription(updated.getDescription());
            purchase.setValue(updated.getValue());
            return ResponseEntity.ok(purchaseService.save(purchase));
        }).orElse(ResponseEntity.notFound().build());
    }

//--------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
//--------------------------------------------------------------------------
    @GetMapping("/greater-than")
    public ResponseEntity<List<Purchase>> getPurchasesGreaterThan(@RequestParam BigDecimal value){
    	List<Purchase> purchase =purchaseService.findByValueGreaterThan(value);
    	return ResponseEntity.ok(purchase);
    }
//--------------------------------------------------------------------------
}

