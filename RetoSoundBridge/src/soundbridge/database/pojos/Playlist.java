package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Playlist implements Serializable {

	private static final long serialVersionUID = 4521317969846887938L;
	private int id = 0;
	private String description = null;
	private Date creationDate = null;

	private ClientP clienteP = null;
	private ClientPp clientePp = null;
	private ArrayList<Contain> contains = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ClientP getClienteP() {
		return clienteP;
	}

	public void setClienteP(ClientP clienteP) {
		this.clienteP = clienteP;
	}

	public ClientPp getClientePp() {
		return clientePp;
	}

	public void setClientePp(ClientPp clientePp) {
		this.clientePp = clientePp;
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
		return Objects.hash(clienteP, contains, creationDate, description, id);
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
		return Objects.equals(clienteP, other.clienteP) && Objects.equals(contains, other.contains)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& id == other.id;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", description=" + description + ", creationDate=" + creationDate + ", clienteP="
				+ clienteP + ", contains=" + contains + ", getId()=" + getId() + ", getDescription()="
				+ getDescription() + ", getCreationDate()=" + getCreationDate() + ", getClienteP()=" + getClienteP()
				+ ", getContains()=" + getContains() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

}
