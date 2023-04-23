package soundbridge.database.pojos;

import java.util.Date;
import java.util.Objects;

public class ClientP extends Client {

	private static final long serialVersionUID = 4111478646411618913L;

	private String bankAccount = null;
	private Date suscriptionDate = null;

	private Playlist playlist = null;

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Date getSuscriptionDate() {
		return suscriptionDate;
	}

	public void setSuscriptionDate(Date suscriptionDate) {
		this.suscriptionDate = suscriptionDate;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bankAccount, suscriptionDate);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientP other = (ClientP) obj;
		return Objects.equals(bankAccount, other.bankAccount)
				&& Objects.equals(suscriptionDate, other.suscriptionDate);
	}

	@Override
	public String toString() {
		return "ClientP [bankAccount=" + bankAccount + ", subscriptionDate=" + suscriptionDate + ", username="
				+ username + ", passwd=" + passwd + ", personalId=" + personalId + ", telephone=" + telephone
				+ ", address=" + address + ", email=" + email + ", id=" + id + ", name=" + name + ", lastName="
				+ lastName + ", nationality=" + nationality + ", gender=" + gender + ", birthDate=" + birthDate
				+ ", getBankAccount()=" + getBankAccount() + ", getSubscriptionDate()=" + getSuscriptionDate()
				+ ", hashCode()=" + hashCode() + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getLastName()=" + getLastName() + ", getNationality()=" + getNationality() + ", getGender()="
				+ getGender() + ", getBirthDate()=" + getBirthDate() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}

}
