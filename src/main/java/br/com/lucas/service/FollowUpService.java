package br.com.lucas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lucas.enums.FollowupStatus;
import br.com.lucas.model.FollowUp;
import br.com.lucas.model.Purchase;
import br.com.lucas.repository.FollowUpRepository;
import br.com.lucas.repository.PurchaseRepository;

@Service
public class FollowUpService {

    private final FollowUpRepository followUpRepository;
//---------------------------------------------------------------------------
    public FollowUpService(FollowUpRepository followUpRepository) {
        this.followUpRepository = followUpRepository;
    }

//---------------------------------------------------------------------------
    public List<FollowUp> findAll() {
        return followUpRepository.findAll();
    }

//---------------------------------------------------------------------------
    public Optional<FollowUp> findById(Long id) {
        return followUpRepository.findById(id);
    }

//---------------------------------------------------------------------------
    public FollowUp save(FollowUp followUp) {
        return followUpRepository.save(followUp);
    }

//---------------------------------------------------------------------------
    public void deleteById(Long id) {
        followUpRepository.deleteById(id);
    }

//---------------------------------------------------------------------------
    public List<FollowUp> findByClientId(Long clientId) {
        return followUpRepository.findByClientId(clientId);
    }

//---------------------------------------------------------------------------
    public List<FollowUp> findByStatus(FollowupStatus status) {
        return followUpRepository.findByStatus(status);
    }

//---------------------------------------------------------------------------
	public List<FollowUp> saveAll(List<FollowUp> followUps) {
		return followUpRepository.saveAll(followUps);
	}
//---------------------------------------------------------------------------

}

