package soundbridge.database.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Person implements Serializable {
	
private static final long serialVersionUID = 6087647728383804140L;
	
	protected int id = 0;
	protected String name = null;
	protected String lastName = null;
	protected String nationality = null;
	protected String gender = null;
	protected Date birthDate = null;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, gender, id, lastName, name, nationality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(gender, other.gender) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(name, other.name)
				&& Objects.equals(nationality, other.nationality);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", nationality=" + nationality
				+ ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

}
