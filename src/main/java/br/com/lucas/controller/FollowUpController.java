package br.com.lucas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.enums.FollowupStatus;
import br.com.lucas.model.FollowUp;
import br.com.lucas.service.FollowUpService;

@RestController
@RequestMapping("/api/followups")
public class FollowUpController {

    private final FollowUpService followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @GetMapping
    public ResponseEntity<List<FollowUp>> getAllFollowUps() {
        return ResponseEntity.ok(followUpService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUp> getById(@PathVariable Long id) {
        return followUpService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<FollowUp>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(followUpService.findByClientId(clientId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FollowUp>> getByStatus(@PathVariable FollowupStatus status) {
        return ResponseEntity.ok(followUpService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<FollowUp> create(@RequestBody FollowUp followUp) {
        return ResponseEntity.ok(followUpService.save(followUp));
    }
    
//  CREATE ARRAY JSON
  @PostMapping("/batch")
  public ResponseEntity<List<FollowUp>> createFollowups(@RequestBody List<FollowUp> followUps) {
      List<FollowUp> saved = followUpService.saveAll(followUps);
      return ResponseEntity.ok(saved);
  }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUp> update(@PathVariable Long id, @RequestBody FollowUp updated) {
        return followUpService.findById(id).map(f -> {
            f.setObservation(updated.getObservation());
            f.setDate(updated.getDate());
            f.setStatus(updated.getStatus());
            return ResponseEntity.ok(followUpService.save(f));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        followUpService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

