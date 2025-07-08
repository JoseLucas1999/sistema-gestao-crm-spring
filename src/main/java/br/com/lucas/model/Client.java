package br.com.lucas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lucas.enums.ClientClassification;
import br.com.lucas.enums.ClientGender;
import br.com.lucas.enums.ClientState;
import br.com.lucas.enums.ContactMethod;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 80)
	private String name;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	private ClientGender gender;
	
	@Column(length = 2)
	private ClientState state;
	
	@Column(nullable = false, length = 12)
	private String cpf;
	
	@Column(length = 35)
	private String email;
	
	@Column(nullable = false, length = 12)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private ContactMethod preferredContactMethod;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private ClientClassification classification;
	
	@JsonIgnore // impede que seje puxado nos endpoints
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Purchase> purchase;
	
	@Column(length = 4)
	private int loyaltyScore;
	
	@JsonIgnore // impede que seje puxado nos endpoints
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<FollowUp> followUp;
	
	public int getAge() {
		if (this.birthDate == null) return 0;
		int age = Period.between(birthDate, LocalDate.now()).getYears();;
		return age;
	}
	
	public Client() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLoyaltyScore() {
		return loyaltyScore;
	}

	public void setLoyaltyScore(int loyaltyScore) {
		this.loyaltyScore = loyaltyScore;
	}

	public ContactMethod getPreferredContactMethod() {
		return preferredContactMethod;
	}

	public void setPreferredContactMethod(ContactMethod preferredContactMethod) {
		this.preferredContactMethod = preferredContactMethod;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public ClientGender getGender() {
		return gender;
	}

	public void setGender(ClientGender gender) {
		this.gender = gender;
	}

	public ClientState getState() {
		return state;
	}

	public void setState(ClientState state) {
		this.state = state;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ClientClassification getClassification() {
		return classification;
	}

	public void setClassification(ClientClassification classification) {
		this.classification = classification;
	}

	public List<Purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}

	public List<FollowUp> getFollowUp() {
		return followUp;
	}

	public void setFollowUp(List<FollowUp> followUp) {
		this.followUp = followUp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, classification, cpf, email, followUp, gender, id, loyaltyScore, name, phone,
				preferredContactMethod, purchase, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(birthDate, other.birthDate) && classification == other.classification
				&& Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(followUp, other.followUp) && gender == other.gender && Objects.equals(id, other.id)
				&& loyaltyScore == other.loyaltyScore && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone) && preferredContactMethod == other.preferredContactMethod
				&& Objects.equals(purchase, other.purchase) && state == other.state;
	}
	
}
