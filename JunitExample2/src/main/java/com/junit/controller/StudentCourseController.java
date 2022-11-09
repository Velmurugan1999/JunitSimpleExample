package com.junit.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junit.models.Course;
import com.junit.models.Student;
import com.junit.service.ServiceClass;
import com.junit.service.StudentDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/studentcourse")
@Tag(name="Student and Course Controller",description = "Student and Course management API")
public class StudentCourseController {
	@Autowired
	ServiceClass service;
	
	
	@GetMapping("/allstudent")
	@Operation(summary = "All Students",description = "Get All Student Details")
	public ResponseEntity<List<Student>> findAllStudent(){
		return new ResponseEntity<List<Student>>(service.findAllStudents(),HttpStatus.OK);
	}
	
	@GetMapping("/allcourse")
	@Operation(summary = "All Course",description = "Get all course details")
	public  ResponseEntity<List<Course>> findAllCourse(){
		
		return new  ResponseEntity<List<Course>>( service.findAllCourse(),HttpStatus.OK);
	}
	
	@GetMapping("/course/{cid}")
	@Operation(summary = "Course By ID", description = "Get course details by course ID")
	public  ResponseEntity<Course> courseById(@PathVariable int cid)
	{
		return new  ResponseEntity<Course>(service.findCourseById(cid),HttpStatus.OK);
	}
	
	@GetMapping("/student/{sid}")
	@Operation(summary = "Student By ID",description = "Get Student details by Student ID")
	public  ResponseEntity<Student> studentById(@PathVariable int sid)
	{
		return new  ResponseEntity<Student>(service.findStudentById(sid),HttpStatus.OK);
	}
	
	@PostMapping(path="/student")
	@Operation(summary = "Insert new Student",description = "Insert new Student into DB")
	public  ResponseEntity<Student> addStudent(@RequestBody Student student) {
		return new  ResponseEntity<Student>(service.saveStudent(student),HttpStatus.OK);
	}
	
	@PostMapping(path="/course")
	@Operation(summary = "Insert new Course",description = "Insert new Course into DB")
	public  ResponseEntity<Course> addCourse(@RequestBody Course course)
	{
		return new  ResponseEntity<Course>(service.newCourse(course),HttpStatus.OK);
	}
	
	@Operation(summary = "Update Student details",description = "Update Student Details")
	@PutMapping(path="/student")
	public  ResponseEntity<Student> updateStudent(@RequestBody StudentDto student)
	{
		return new  ResponseEntity<Student>(service.updateStudent(student),HttpStatus.OK);
	}
	
	@PutMapping(path="/course")
	@Operation(summary = "Update Course details",description = "Update Course Details")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course)
	{
		return new ResponseEntity<Course>(service.updateCourse(course),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/student/{sid}")
	@Operation(summary = "Delete Student by ID",description = "Delete Student details by Student ID")
	public void deleteStudent(@PathVariable int sid)
	{
		service.deleteStudent(sid);
	}
}
