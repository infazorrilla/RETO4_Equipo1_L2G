package soundbridge.database.views.pojos;

import java.util.Objects;

/**
 * Describes the Top20 view on database, which contains the 20 most listened
 * songs.
 */
public class Top20 {

	private int id = 0;
	private String name = null;
	private int plays = 0;

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

	public int getPlays() {
		return plays;
	}

	public void setPlays(int plays) {
		this.plays = plays;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, plays);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Top20 other = (Top20) obj;
		return id == other.id && Objects.equals(name, other.name) && plays == other.plays;
	}

	@Override
	public String toString() {
		return "Top20 [id=" + id + ", name=" + name + ", plays=" + plays + "]";
	}

}
