package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Describes a review of the database.
 */
public class Review implements Serializable {

	private static final long serialVersionUID = 8061478950534136455L;

	private int stars = 0;
	private String title = null;
	private String opinion = null;
	private Date reviewDate = null;
	private boolean isValidated = false;

	private ClientPP clientPP = null;
	private Album album = null;

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	public ClientPP getClientPP() {
		return clientPP;
	}

	public void setClientPP(ClientPP clientPP) {
		this.clientPP = clientPP;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, clientPP, isValidated, opinion, reviewDate, stars, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(album, other.album) && Objects.equals(clientPP, other.clientPP)
				&& isValidated == other.isValidated && Objects.equals(opinion, other.opinion)
				&& Objects.equals(reviewDate, other.reviewDate) && stars == other.stars
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Review [stars=" + stars + ", title=" + title + ", opinion=" + opinion + ", reviewDate=" + reviewDate
				+ ", isValidated=" + isValidated + ", clientPP=" + clientPP + ", album=" + album + "]";
	}

}
