package com.app.bak.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.app.bak.enums.Gender;
import com.app.bak.enums.MaritalStatus;
import com.app.bak.enums.Role;

@Entity
@Table(name = "EMPLOYEE")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(
				name = "findLessSalariesEmployees", 
				procedureName = "FIND_LESS_SALARIS_EMPLOYEES", 
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "IN_SALARY", type = Double.class) 
				},
				resultClasses = { EmployeeEntity.class }
		) })
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEE_ID")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(initialValue = 1, name = "emp_seq", sequenceName = "EMPLOYEE_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
	private int employeeId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDL_ENAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FATHER_NAME")
	private String fatherName;

	@Column(name = "MOTHER_NAME")
	private String motherName;

	@Column(name = "GURDIAN_NAME")
	private String gurdianName;

	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "SALARY")
	private double salary;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "ALTERNATE_NUMBER")
	private String alternateNumber;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MARITAL_STATUS")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Column(name = "SPOUSE_NAME")
	private String spouseName;

	@OneToMany(targetEntity = AddressEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "EMPLOYEE_REF_ID", referencedColumnName = "EMPLOYEE_ID")
	private List<AddressEntity> addressList;

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @return the gurdianName
	 */
	public String getGurdianName() {
		return gurdianName;
	}

	/**
	 * @param gurdianName the gurdianName to set
	 */
	public void setGurdianName(String gurdianName) {
		this.gurdianName = gurdianName;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the alternateNumber
	 */
	public String getAlternateNumber() {
		return alternateNumber;
	}

	/**
	 * @param alternateNumber the alternateNumber to set
	 */
	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the maritalStatus
	 */
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}

	/**
	 * @param spouseName the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	/**
	 * @return the addressList
	 */
	public List<AddressEntity> getAddressList() {
		return addressList;
	}

	/**
	 * @param addressList the addressList to set
	 */
	public void setAddressList(List<AddressEntity> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", fatherName=" + fatherName + ", motherName=" + motherName
				+ ", gurdianName=" + gurdianName + ", role=" + role + ", salary=" + salary + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", alternateNumber="
				+ alternateNumber + ", email=" + email + ", maritalStatus=" + maritalStatus + ", spouseName="
				+ spouseName + ", addressList=" + addressList + "]";
	}

}
