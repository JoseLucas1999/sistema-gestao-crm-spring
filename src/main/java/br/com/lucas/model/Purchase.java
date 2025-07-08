package br.com.lucas.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500)
	private String description;
	
	@Temporal(TemporalType.DATE) //indica que é um date
	private LocalDate date;
	
	@Column(length = 10)
	private BigDecimal value;
	
	@ManyToOne
	private Client client;
	
	public Purchase() {}

	public Purchase(Long id, String description, LocalDate date, BigDecimal value, Client client) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.value = value;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, date, description, id, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		return Objects.equals(client, other.client) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(value, other.value);
	}
}
