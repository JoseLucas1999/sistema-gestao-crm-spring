package br.com.lucas.model;

import java.time.LocalDate;
import java.util.Objects;

import br.com.lucas.enums.ClientClassification;
import br.com.lucas.enums.FollowupStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class FollowUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500)
	private String observation;
	
	@Temporal(TemporalType.DATE) //indica que é um date
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FollowupStatus status;
	
	@ManyToOne
	private Client client;
	
	public FollowUp() {}

	public FollowUp(Long id, String observation, LocalDate date, FollowupStatus status, Client client) {
		super();
		this.id = id;
		this.observation = observation;
		this.date = date;
		this.status = status;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public FollowupStatus getStatus() {
		return status;
	}

	public void setStatus(FollowupStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, date, id, observation, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowUp other = (FollowUp) obj;
		return Objects.equals(client, other.client) && Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(observation, other.observation) && Objects.equals(status, other.status);
	}
}
