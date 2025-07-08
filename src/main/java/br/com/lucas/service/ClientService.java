package br.com.lucas.service;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.lucas.dto.ClientLoyaltyDTO;
import br.com.lucas.enums.ClientClassification;
import br.com.lucas.enums.ClientGender;
import br.com.lucas.enums.ClientState;
import br.com.lucas.model.Client;
import br.com.lucas.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
//----------------------------------------------------------------
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
//----------------------------------------------------------------
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
//----------------------------------------------------------------
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
//----------------------------------------------------------------
//    salva um único JSON
    public Client save(Client client) {
        return clientRepository.save(client);
    }
//----------------------------------------------------------------
//	salva um array de JSON
    public List<Client> saveAll(List<Client> clients) {
		return clientRepository.saveAll(clients);
	}
//----------------------------------------------------------------
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
//----------------------------------------------------------------
    public List<Client> findByClassification(ClientClassification classification) {
        return clientRepository.findByClassification(classification);
    }
//----------------------------------------------------------------
    public List<Client> findByGender(ClientGender gender){
    	return clientRepository.findByGender(gender);
    }
//----------------------------------------------------------------
    public List<Client> findByState(ClientState state){
    	return clientRepository.findByState(state);
    }
//----------------------------------------------------------------
    public List<Client> searchByName(String name) {
        return clientRepository.findByNameContainingIgnoreCase(name);
    }
//----------------------------------------------------------------
    
//  COUNT BY CLASSIFICATION
    public Map<ClientClassification, Long> getClientCountByClassification(){
    	List<Object[]> result = clientRepository.countClientsByClassification();
    	Map<ClientClassification, Long> map = new EnumMap<>(ClientClassification.class);
    	for(Object[] row : result) {
    		ClientClassification classification = (ClientClassification) row[0];
    		Long count = (Long) row[1];
    		map.put(classification, count);
    	}
    	return map;
    }
//----------------------------------------------------------------
    
//    COUNT BY STATES
    public Map<ClientState, Long> getClientCountByState(){
    	List<Object[]> result = clientRepository.countClientsByState();
    	Map<ClientState, Long> map = new LinkedHashMap<>();
    	for(Object[] row : result) {
    		ClientState state = (ClientState) row[0];
    		Long count = (Long) row[1];
    		map.put(state, count);
    	}
    	return map;
    }
//----------------------------------------------------------------
    public Map<ClientGender, Double> getGenderPercentage() {
        List<Object[]> result = clientRepository.countClientsByGender();
        long total = clientRepository.count();
        
        Map<ClientGender, Double> percentageMap = new EnumMap<>(ClientGender.class);

        for (Object[] row : result) {
            ClientGender gender = (ClientGender) row[0];
            Long count = (Long) row[1];
            double percentage = (double) count * 100 / total;
            percentageMap.put(gender, Math.round(percentage * 10.0) / 10.0); // arredonda para 1 casa decimal
        }

        return percentageMap;
    }

//----------------------------------------------------------------
    
    public List<ClientLoyaltyDTO> getLoyaltyRankingDTO() {
        List<Client> clients = clientRepository.findAllByOrderByLoyaltyScoreDesc();
        return clients.stream()
                .map(c -> new ClientLoyaltyDTO(c.getName(), c.getCpf(), c.getPhone(), c.getLoyaltyScore()))
                .collect(Collectors.toList());
    }

//----------------------------------------------------------------

}
