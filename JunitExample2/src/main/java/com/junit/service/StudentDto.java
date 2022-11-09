package com.junit.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StudentDto {
	private int studid;
	private String name;
	private String address;
	private String mobileno;
	private double grade;
	private List<Integer> courses;
	
	public StudentDto() {
		
	}
	
	
	
	public StudentDto(int studid, String name, String address, String mobileno, double grade, List<Integer> courses) {
		super();
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
	public List<Integer> getCourses() {
		return courses;
	}
	public void setCourses(List<Integer> courses) {
		this.courses = courses;
	}
	
	
}
