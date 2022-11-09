package com.junit.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Student Id Auto-Generated")
	private int studid;
	@Schema(description = "Student Name")
	private String name;
	@Schema(description = "City only")
	private String address;
	@Schema(description = "Starts with +91")
	private String mobileno;
	@Schema(description = "Should be less then 10")
	private double grade;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="student_course",joinColumns = @JoinColumn(name="stud_id"),inverseJoinColumns = @JoinColumn(name="course_id"))
	private List<Course> courses;
	
	public Student() {
		
	}
	
	public Student(String name, String address, String mobileno, double grade, List<Course> courses) {
		this.name = name;
		this.address = address;
		this.mobileno = mobileno;
		this.grade = grade;
		this.courses = courses;
	}

	
	public Student(int studid, String name, String address, String mobileno, double grade, List<Course> courses) {
		this.studid = studid;
		this.name = name;
		this.address = address;
		this.mobileno = mobileno;
		this.grade = grade;
		this.courses = courses;
	}



	public int getStudid() {
		return studid;
	}
	public void setStudid(int studid) {
		this.studid = studid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
