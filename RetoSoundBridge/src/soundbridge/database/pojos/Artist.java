package soundbridge.database.pojos;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Describes an artist of the database.
 */
public class Artist extends Person {

	private static final long serialVersionUID = 5040901852828729717L;

	private String description = null;
	private String role = null;
	private String image = null;

	private ArtGroup artGroup = null;
	private ArrayList<Song> song = null;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArtGroup getArtGroup() {
		return artGroup;
	}

	public void setArtGroup(ArtGroup artGroup) {
		this.artGroup = artGroup;
	}

	public ArrayList<Song> getSong() {
		return song;
	}

	public void setSong(ArrayList<Song> song) {
		this.song = song;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(artGroup, description, image, role, song);
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
		Artist other = (Artist) obj;
		return Objects.equals(artGroup, other.artGroup) && Objects.equals(description, other.description)
				&& Objects.equals(image, other.image) && Objects.equals(role, other.role)
				&& Objects.equals(song, other.song);
	}

	@Override
	public String toString() {
		return "Artist [description=" + description + ", role=" + role + ", image=" + image + ", artGroup=" + artGroup
				+ ", song=" + song + ", id=" + id + ", name=" + name + ", lastName=" + lastName + ", nationality="
				+ nationality + ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

}
