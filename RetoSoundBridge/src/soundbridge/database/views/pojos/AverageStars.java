package soundbridge.database.views.pojos;

import java.util.Objects;

public class AverageStars {

	private int idAlbum = 0;
	private String name = null;
	private double average = 0;

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	@Override
	public int hashCode() {
		return Objects.hash(average, idAlbum, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AverageStars other = (AverageStars) obj;
		return Double.doubleToLongBits(average) == Double.doubleToLongBits(other.average) && idAlbum == other.idAlbum
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "AverageStars [idAlbum=" + idAlbum + ", name=" + name + ", average=" + average + "]";
	}

}
