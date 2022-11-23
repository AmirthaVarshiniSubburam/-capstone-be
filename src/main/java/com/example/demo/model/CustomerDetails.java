package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "CustomerDetails")
public class CustomerDetails {
	
	@Id
	private String email;
	private String firstname;
	private String lastname;
	private Long accountNo;
	private Long mobileNo;
	private String password;
	
	public CustomerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDetails(String email, String firstname, String lastname, Long accountNo, Long mobileNo,
			String password) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountNo = accountNo;
		this.mobileNo = mobileNo;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String encryptedPwd = pwdEncoder.encode(password);
		this.password = encryptedPwd;
	}
	
	public void setNewPassword(String password) {
//		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
//		String encryptedPwd = pwdEncoder.encode(password);
		this.password = password;
	}


	@Override
	public String toString() {
		return "CustomerDetails [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", accountNo=" + accountNo + ", mobileNo=" + mobileNo + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNo, email, firstname, lastname, mobileNo, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetails other = (CustomerDetails) obj;
		return Objects.equals(accountNo, other.accountNo) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(mobileNo, other.mobileNo) && Objects.equals(password, other.password);
	}
		
}
