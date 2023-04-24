package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Song implements Serializable {

	private static final long serialVersionUID = 4944962431162757875L;
	private int id = 0;
	private String name = null;
	private Date cretaion = null;
	private int duration = 0;
	private String cover = null;
	private String lang = null;
	private String source = null;

	private ArrayList<Play> plays = null;
	private ArrayList<Contain> contains = null;

	private Album album = null;
	private Artist artist = null;
	private ArtGroup artGroup = null;

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

	public Date getCretaion() {
		return cretaion;
	}

	public void setCretaion(Date cretaion) {
		this.cretaion = cretaion;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public ArrayList<Play> getPlays() {
		return plays;
	}

	public void setPlays(ArrayList<Play> plays) {
		this.plays = plays;
	}

	public ArrayList<Contain> getContains() {
		return contains;
	}

	public void setContains(ArrayList<Contain> contains) {
		this.contains = contains;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public ArtGroup getArtGroup() {
		return artGroup;
	}

	public void setArtGroup(ArtGroup artGroup) {
		this.artGroup = artGroup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, artGroup, artist, contains, cover, cretaion, duration, id, lang, name, plays,
				source);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(album, other.album) && Objects.equals(artGroup, other.artGroup)
				&& Objects.equals(artist, other.artist) && Objects.equals(contains, other.contains)
				&& Objects.equals(cover, other.cover) && Objects.equals(cretaion, other.cretaion)
				&& duration == other.duration && id == other.id && Objects.equals(lang, other.lang)
				&& Objects.equals(name, other.name) && Objects.equals(plays, other.plays)
				&& Objects.equals(source, other.source);
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", cretaion=" + cretaion + ", duration=" + duration + ", cover="
				+ cover + ", lang=" + lang + ", source=" + source + ", plays=" + plays + ", contains=" + contains
				+ ", album=" + album + ", artist=" + artist + ", artGroup=" + artGroup + "]";
	}

}
