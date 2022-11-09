package com.junit.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String cname;
	private String staff_name;
	private int duration;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
	private List<Student> students;
	
	public Course() {
		
	}

	public Course(String cname, String staff_name, int duration, List<Student> students) {
		this.cname = cname;
		this.staff_name = staff_name;
		this.duration = duration;
		this.students = students;
	}
	public Course(int cid, String cname, String staff_name, int duration, List<Student> students) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.staff_name = staff_name;
		this.duration = duration;
		this.students = students;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
