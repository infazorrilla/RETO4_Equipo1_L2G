package soundbridge.database.views.pojos;

import java.util.Objects;

public class AverageStars {

	private int id = 0;
	private String name = null;
	private double average = 0;

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

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	@Override
	public int hashCode() {
		return Objects.hash(average, id, name);
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
		return Double.doubleToLongBits(average) == Double.doubleToLongBits(other.average) && id == other.id
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "AverageStars [id=" + id + ", name=" + name + ", average=" + average + "]";
	}

}
