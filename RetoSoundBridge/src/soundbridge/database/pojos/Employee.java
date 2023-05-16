package soundbridge.database.pojos;

import java.util.Date;
import java.util.Objects;

/**
 * Describes an employee of the database.
 */
public class Employee extends Person {

	private static final long serialVersionUID = 9069804610454523019L;

	private String personalId = null;
	private String telephone = null;
	private String email = null;
	private String address = null;
	private String username = null;
	private String passwd = null;
	private String ssNum = null;
	private int salary = 0;
	private Date hireDate = null;

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSsNum() {
		return ssNum;
	}

	public void setSsNum(String ssNum) {
		this.ssNum = ssNum;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(address, email, hireDate, passwd, personalId, salary, ssNum, telephone, username);
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
		Employee other = (Employee) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(hireDate, other.hireDate) && Objects.equals(passwd, other.passwd)
				&& Objects.equals(personalId, other.personalId) && salary == other.salary
				&& Objects.equals(ssNum, other.ssNum) && Objects.equals(telephone, other.telephone)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Employee [personalId=" + personalId + ", telephone=" + telephone + ", email=" + email + ", address="
				+ address + ", username=" + username + ", passwd=" + passwd + ", ssNum=" + ssNum + ", salary=" + salary
				+ ", hireDate=" + hireDate + ", id=" + id + ", name=" + name + ", lastName=" + lastName
				+ ", nationality=" + nationality + ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

}
