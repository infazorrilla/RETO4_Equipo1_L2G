package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Contain implements Serializable {

	private static final long serialVersionUID = 2875787677995476551L;

	private Playlist playlist = null;
	private Song song = null;

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
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
		return Objects.hash(playlist);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contain other = (Contain) obj;
		return Objects.equals(playlist, other.playlist);
	}

	@Override
	public String toString() {
		return "Contain [playlist=" + playlist + ", song=" + song + "]";
	}

}
