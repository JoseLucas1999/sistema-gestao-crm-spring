package br.com.lucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucas.enums.FollowupStatus;
import br.com.lucas.model.FollowUp;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
    List<FollowUp> findByClientId(Long clientId);
    List<FollowUp> findByStatus(FollowupStatus status);
}
