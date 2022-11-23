package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="LoanAplicationForm")

public class LoanApp {
	
	@Id
//	@SequenceGenerator(name="mysequence", initialValue=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long loanid;
	private Long mobno;
	private String loantype;
	private String fname;
	private String lname;
	private String occupation;	
	private String city;
	private String address;
	private String state;
	private Long zip;
	private Long amount;
	private Integer tenure;
	public LoanApp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoanApp(Long loanid, Long mobno, String loantype, String fname, String lname, String occupation, String city,
			String address, String state, Long zip, Long amount, Integer tenure) {
		super();
		this.loanid = loanid;
		this.mobno = mobno;
		this.loantype = loantype;
		this.fname = fname;
		this.lname = lname;
		this.occupation = occupation;
		this.city = city;
		this.address = address;
		this.state = state;
		this.zip = zip;
		this.amount = amount;
		this.tenure = tenure;
	}
	public Long getLoanid() {
		return loanid;
	}
	public void setLoanid(Long loanid) {
		this.loanid = loanid;
	}
	public Long getMobno() {
		return mobno;
	}
	public void setMobno(Long mobno) {
		this.mobno = mobno;
	}
	public String getLoantype() {
		return loantype;
	}
	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getZip() {
		return zip;
	}
	public void setZip(Long zip) {
		this.zip = zip;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Integer getTenure() {
		return tenure;
	}
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}
	@Override
	public String toString() {
		return "LoanApp [loanid=" + loanid + ", mobno=" + mobno + ", loantype=" + loantype + ", fname=" + fname
				+ ", lname=" + lname + ", occupation=" + occupation + ", city=" + city + ", address=" + address
				+ ", state=" + state + ", zip=" + zip + ", amount=" + amount + ", tenure=" + tenure + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, amount, city, fname, lname, loanid, loantype, mobno, occupation, state, tenure,
				zip);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanApp other = (LoanApp) obj;
		return Objects.equals(address, other.address) && Objects.equals(amount, other.amount)
				&& Objects.equals(city, other.city) && Objects.equals(fname, other.fname)
				&& Objects.equals(lname, other.lname) && Objects.equals(loanid, other.loanid)
				&& Objects.equals(loantype, other.loantype) && Objects.equals(mobno, other.mobno)
				&& Objects.equals(occupation, other.occupation) && Objects.equals(state, other.state)
				&& Objects.equals(tenure, other.tenure) && Objects.equals(zip, other.zip);
	}
	
	
	
}
