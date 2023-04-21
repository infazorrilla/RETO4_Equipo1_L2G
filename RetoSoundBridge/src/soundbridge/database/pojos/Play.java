package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Play implements Serializable {

	private static final long serialVersionUID = 3973834720207596222L;

	private int id = 0;
	private Date reproductionDate = null;

	private Client client = null;
	private Song song = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReproductionDate() {
		return reproductionDate;
	}

	public void setReproductionDate(Date reproductionDate) {
		this.reproductionDate = reproductionDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, id, reproductionDate, song);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Play other = (Play) obj;
		return Objects.equals(client, other.client) && id == other.id
				&& Objects.equals(reproductionDate, other.reproductionDate) && Objects.equals(song, other.song);
	}

	@Override
	public String toString() {
		return "Play [id=" + id + ", reproductionDate=" + reproductionDate + ", client=" + client + ", song=" + song
				+ "]";
	}

}
