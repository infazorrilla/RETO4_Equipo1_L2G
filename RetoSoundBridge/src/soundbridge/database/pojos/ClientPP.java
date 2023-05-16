package soundbridge.database.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Describes a Premium Plus Client of the database.
 */
public class ClientPP extends Client {

	private static final long serialVersionUID = -7048604268786892326L;

	private String bankAccount = null;
	private Date suscriptionDate = null;

	private ArrayList<Playlist> playlists = null;
	private ArrayList<Review> reviews = null;

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

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bankAccount, playlists, reviews, suscriptionDate);
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
		ClientPP other = (ClientPP) obj;
		return Objects.equals(bankAccount, other.bankAccount) && Objects.equals(playlists, other.playlists)
				&& Objects.equals(reviews, other.reviews) && Objects.equals(suscriptionDate, other.suscriptionDate);
	}

	@Override
	public String toString() {
		return "ClientPP [bankAccount=" + bankAccount + ", suscriptionDate=" + suscriptionDate + ", playlists="
				+ playlists + ", reviews=" + reviews + ", username=" + username + ", passwd=" + passwd + ", personalId="
				+ personalId + ", telephone=" + telephone + ", address=" + address + ", email=" + email + ", plays="
				+ plays + ", id=" + id + ", name=" + name + ", lastName=" + lastName + ", nationality=" + nationality
				+ ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

}
