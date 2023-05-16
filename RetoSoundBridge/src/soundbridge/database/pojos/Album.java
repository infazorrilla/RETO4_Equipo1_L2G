package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Describes an album of the database.
 */
public class Album implements Serializable {

	private static final long serialVersionUID = -4101493757142816718L;

	private int id = 0;
	private String name = null;
	private Date releaseYear = null;
	private String cover = null;

	private ArrayList<Review> reviews = null;
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

	public Date getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
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
		return Objects.hash(cover, id, name, releaseYear, reviews, songs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(cover, other.cover) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(releaseYear, other.releaseYear) && Objects.equals(reviews, other.reviews)
				&& Objects.equals(songs, other.songs);
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", releaseYear=" + releaseYear + ", cover=" + cover + ", reviews="
				+ reviews + ", songs=" + songs + "]";
	}

}
