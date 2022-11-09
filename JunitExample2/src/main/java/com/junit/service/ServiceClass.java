package com.junit.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junit.dao.CourseDao;
import com.junit.dao.StudentDao;
import com.junit.models.Course;
import com.junit.models.Student;


@Service
public class ServiceClass {
	@Autowired
	CourseDao cdao;
	@Autowired
	StudentDao sdao;
	
	public List<Student> findAllStudents(){
		return sdao.findAll();
	}
	
	public Student findStudentById(int sid)
	{
		return sdao.findById(sid).orElseThrow(()->new EntityNotFoundException("No Student with given ID"));
	}
	
	public Student saveStudent(Student student)
	{
		return sdao.save(student);
	}
	
	public Student updateStudent(StudentDto student)
	{
		Student stu=sdao.findById(student.getStudid()).orElseThrow(()->new EntityNotFoundException("No Student with given ID"));
		stu.getCourses().clear();
		for(Integer i:student.getCourses())
		{
			stu.getCourses().add( cdao.findById(i).orElseThrow(()->new EntityNotFoundException("No Course with given ID")));
		}
		
		return sdao.save(stu);
	}
	
	
	
	public Course findCourseById(int cid)
	{
		return cdao.findById(cid).orElseThrow(()->new EntityNotFoundException("No Course with given ID"));
	}
	
	public List<Course> findAllCourse(){
		return cdao.findAll();
	}
	public Course newCourse(Course course)
	{
		return cdao.save(course);
	}
	
	public Course updateCourse(Course course)
	{
		cdao.findById(course.getCid()).orElseThrow(()->new EntityNotFoundException("No Course with given ID"));
		return cdao.save(course);
	}
	
	public void deletestudent(Student student)
	{
		sdao.delete(student);
	}
	
	public void deleteStudent(int sid)
	{
		Student student =sdao.findById(sid).orElseThrow(()->new EntityNotFoundException("Student Id Not Found"));
		student.getCourses().clear();
		sdao.deleteById(sid);
	}
}
