package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Client extends Person implements Serializable {

	private static final long serialVersionUID = -2513971403121032275L;

	protected String username = null;
	protected String passwd = null;
	protected String personalId = null;
	protected String telephone = null;
	protected String address = null;
	protected String email = null;
	protected boolean isBlocked = false;

	protected ArrayList<Play> plays = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public ArrayList<Play> getPlays() {
		return plays;
	}

	public void setPlays(ArrayList<Play> plays) {
		this.plays = plays;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(address, email, isBlocked, passwd, personalId, plays, telephone, username);
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
		Client other = (Client) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& isBlocked == other.isBlocked && Objects.equals(passwd, other.passwd)
				&& Objects.equals(personalId, other.personalId) && Objects.equals(plays, other.plays)
				&& Objects.equals(telephone, other.telephone) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Client [username=" + username + ", passwd=" + passwd + ", personalId=" + personalId + ", telephone="
				+ telephone + ", address=" + address + ", email=" + email + ", isBlocked=" + isBlocked + ", plays="
				+ plays + ", id=" + id + ", name=" + name + ", lastName=" + lastName + ", nationality=" + nationality
				+ ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

}
