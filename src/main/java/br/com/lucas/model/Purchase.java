package br.com.lucas.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.lucas.enums.PurchasePaymentMethod;
import br.com.lucas.enums.Purchasestatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@Column(precision = 10, scale = 2)
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", nullable = false)
	private PurchasePaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Purchasestatus satus;
	
	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseItems> items = new ArrayList<>();
	
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

	public PurchasePaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PurchasePaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Purchasestatus getSatus() {
		return satus;
	}

	public void setSatus(Purchasestatus satus) {
		this.satus = satus;
	}

	public List<PurchaseItems> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItems> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, date, description, id, items, paymentMethod, satus, value);
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
				&& Objects.equals(items, other.items) && paymentMethod == other.paymentMethod && satus == other.satus
				&& Objects.equals(value, other.value);
	}

}
