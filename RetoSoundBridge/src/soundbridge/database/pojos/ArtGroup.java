package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ArtGroup implements Serializable {

	private static final long serialVersionUID = 4045356093574099733L;

	private int id = 0;
	private String name = null;
	private String description = null;

	private ArrayList<Artist> artists = null;
	private ArrayList<Song> songs = null;

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

	public ArrayList<Artist> getArtists() {
		return artists;
	}

	public void setArtists(ArrayList<Artist> artists) {
		this.artists = artists;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artists, description, id, name, songs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtGroup other = (ArtGroup) obj;
		return Objects.equals(artists, other.artists) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(songs, other.songs);
	}

	@Override
	public String toString() {
		return "ArtGroup [id=" + id + ", name=" + name + ", description=" + description + ", artists=" + artists
				+ ", songs=" + songs + "]";
	}

}
