package br.com.lucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lucas.enums.ClientClassification;
import br.com.lucas.enums.ClientGender;
import br.com.lucas.enums.ClientState;
import br.com.lucas.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByClassification(ClientClassification classification);
    List<Client> findByGender(ClientGender gender);
    List<Client> findByState(ClientState state);
    List<Client> findByNameContainingIgnoreCase(String name);
    @Query("SELECT c.classification, COUNT(c) FROM Client c GROUP BY c.classification")
    List<Object[]> countClientsByClassification();
    
    @Query("SELECT c.state, COUNT(c) FROM Client c GROUP BY c.state ORDER BY COUNT(c) ASC")
    List<Object[]> countClientsByState();
    
    @Query("SELECT c.gender, COUNT(c) FROM Client c GROUP BY c.gender")
    List<Object[]> countClientsByGender();
    
    List<Client> findAllByOrderByLoyaltyScoreDesc();
    List<Client> findAllByOrderByLoyaltyScoreAsc();
    


    
//    @Query("SELECT c FROM Client c WHERE FUNCTION('YEAR', CURRENT_DATE) - FUNCTION('YEAR', c.birthDate) BETWEEN :min AND :max")
//    List<Client> findByAgeRange(@Param("min") int min, @Param("max") int max);

}

//interface que estende JpaRepository
//	SELECT * FROM client WHERE classification = ?;
