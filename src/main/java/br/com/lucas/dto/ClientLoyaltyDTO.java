package br.com.lucas.dto;

import java.util.Objects;

public class ClientLoyaltyDTO {

    private String name;
    private String cpf;
    private String phone;
    private int loyaltyScore;

    public ClientLoyaltyDTO() {super();}

    public ClientLoyaltyDTO(String name, String cpf, String phone, int loyaltScore) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.loyaltyScore = loyaltScore;
    }

	public String getName() { return name; }
    public String getCpf() { return cpf; }
    public String getPhone() { return phone; }
    public int getLoyaltScore() { return loyaltyScore; }

	@Override
	public int hashCode() {
		return Objects.hash(cpf, loyaltyScore, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientLoyaltyDTO other = (ClientLoyaltyDTO) obj;
		return Objects.equals(cpf, other.cpf) && loyaltyScore == other.loyaltyScore && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}
}
