package br.com.lucas.controller;


import java.util.List;
import java.util.Map;

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

import br.com.lucas.dto.ClientLoyaltyDTO;
import br.com.lucas.enums.ClientClassification;
import br.com.lucas.enums.ClientGender;
import br.com.lucas.enums.ClientState;
import br.com.lucas.model.Client;
import br.com.lucas.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

//------------------------------------------------------------------------
//    FIND ALL
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

//------------------------------------------------------------------------
//        FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//------------------------------------------------------------------------
    @PostMapping
//    CRETAE
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }
//    CREATE ARRAY JSON
    @PostMapping("/batch")
    public ResponseEntity<List<Client>> createClients(@RequestBody List<Client> clients) {
        List<Client> saved = clientService.saveAll(clients);
        return ResponseEntity.ok(saved);
    }
//------------------------------------------------------------------------
//    UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updated) {
        return clientService.findById(id).map(client -> {
            client.setName(updated.getName());
            client.setEmail(updated.getEmail());
            client.setPhone(updated.getPhone());
            client.setCpf(updated.getCpf());
            client.setClassification(updated.getClassification());
            return ResponseEntity.ok(clientService.save(client));
        }).orElse(ResponseEntity.notFound().build());
    }

//------------------------------------------------------------------------
//    DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//------------------------------------------------------------------------
//    SEARCH BY NAME
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(clientService.searchByName(name));
    }

//------------------------------------------------------------------------
//    FIND BY CLASSIFICATION
    @GetMapping("/classification/{classification}")
    public ResponseEntity<List<Client>> getByClassification(@PathVariable ClientClassification classification) {
        return ResponseEntity.ok(clientService.findByClassification(classification));
    }
//------------------------------------------------------------------------
//    FIND BY GENDER
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Client>> getByGender(@PathVariable ClientGender gender){
    	return ResponseEntity.ok(clientService.findByGender(gender));
    }
//------------------------------------------------------------------------
//    FIND BY STATE
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Client>> getByState(@PathVariable ClientState state){
    	return ResponseEntity.ok(clientService.findByState(state));
    }
//------------------------------------------------------------------------
//    COUNT CLASSIFICATION
    @GetMapping("/classification/count")
    public ResponseEntity<Map<ClientClassification, Long>> getClientCountByClassification(){
    	return ResponseEntity.ok(clientService.getClientCountByClassification());
    }
//------------------------------------------------------------------------
//    COUNT CLASSIFICATION
    @GetMapping("/state/count")
    public ResponseEntity<Map<ClientState, Long>> getClientCountByState(){
    	return ResponseEntity.ok(clientService.getClientCountByState());
    }
    
//------------------------------------------------------------------------
//    GENDER PERCENTAGE
    @GetMapping("/gender/percentage")
    public ResponseEntity<Map<ClientGender, Double>> getGenderPercentage() {
        return ResponseEntity.ok(clientService.getGenderPercentage());
    }
//------------------------------------------------------------------------
//    FIND BY LOYALT RANK 
    @GetMapping("/loyalty/rank")
    public ResponseEntity<List<ClientLoyaltyDTO>> getClientsByLoyaltyRank() {
        return ResponseEntity.ok(clientService.getLoyaltyRankingDTO());
    }

//------------------------------------------------------------------------
}
