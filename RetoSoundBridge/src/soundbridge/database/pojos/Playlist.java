package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Describes a playlist of the database.
 */
public class Playlist implements Serializable {

	private static final long serialVersionUID = 4521317969846887938L;
	
	private int id = 0;
	private String name = null;
	private String description = null;
	private Date creationDate = null;

	private ClientP clientP = null;
	private ClientPP clientPP = null;
	private ArrayList<Contain> contains = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public ClientP getClientP() {
		return clientP;
	}

	public void setClientP(ClientP clientP) {
		this.clientP = clientP;
	}

	public ClientPP getClientPP() {
		return clientPP;
	}

	public void setClientPP(ClientPP clientPP) {
		this.clientPP = clientPP;
	}

	public ArrayList<Contain> getContains() {
		return contains;
	}

	public void setContains(ArrayList<Contain> contains) {
		this.contains = contains;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientP, clientPP, contains, creationDate, description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		return Objects.equals(clientP, other.clientP) && Objects.equals(clientPP, other.clientPP)
				&& Objects.equals(contains, other.contains) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", description=" + description + ", creationDate="
				+ creationDate + ", clientP=" + clientP + ", clientPP=" + clientPP + ", contains=" + contains + "]";
	}

}
