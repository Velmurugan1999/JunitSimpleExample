package com.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.controller.StudentCourseController;
import com.junit.dao.CourseDao;
import com.junit.dao.StudentDao;
import com.junit.models.Course;
import com.junit.models.Student;
import com.junit.service.ServiceClass;
import com.junit.service.StudentDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class JunitExample2ApplicationTests {
	public Logger logger=LoggerFactory.getLogger("junit");
	
	@Autowired
	StudentCourseController controller;
	
	@Autowired
	ServiceClass service;
	
	@MockBean
	StudentDao dao;
	
	@MockBean
	CourseDao cdao;
	
	@BeforeEach
	public void start() {
		logger.info("Test started--------->");
	}
	
	@AfterEach
	public void end() {
		logger.info("Test ended--------->");
	}
	
	public static Stream<Arguments> insertStudents(){
		return Stream.of(Arguments.of(111,"Anand","Salem"),
				Arguments.of(2222,"Vishnu","CBE"),
				Arguments.of(3232,"Priya","Chennai"),
				Arguments.of(12344,"Kavitha","Salem"));		
	}
	
	@ParameterizedTest
	@ValueSource(ints= {22,4,6667,88889})
	public void findAllStudentTest(int id) {
		Student student=new Student(id, "Anand", "Salem", "+919824356527", 8.7,null);
		when(dao.findAll()).thenReturn(Stream.of(student).collect(Collectors.toList()));
		assertEquals(id, service.findAllStudents().get(0).getStudid());
	}
	
	@Test
	public void getByIDStudentTest()
	{
		Optional<Student> student=Optional.of(new Student(222, "Anand", "Salem", "+919824356527", 8.7,null));
		when(dao.findById(anyInt())).thenReturn(student);
		assertEquals(8.7, service.findStudentById(222).getGrade());
	}
	
	@ParameterizedTest
	@MethodSource("insertStudents")
	public void inserstudentTest(int id, String name, String city) {
		Student student=new Student(id, name, city, "+919824356527", 8.7,null);
		when(dao.save(student)).thenReturn(student);
		assertTrue(service.saveStudent(student).getStudid()==id);
	}
	
	@ParameterizedTest
	@ValueSource(ints= {123,44322,3345,65456})
	public void findAllCourseTest(int id) {
		Course course=new Course(id, "Python", "Vishnu", 35, null);
		when(cdao.findAll()).thenReturn(Stream.of(course).collect(Collectors.toList()));
		assertEquals(1, service.findAllCourse().size());
	}
	
	@ParameterizedTest
	@ValueSource(ints= {123,44322,3345,65456})
	public void insertCourseTest(int id) {
		Course course=new Course(id, "Python", "Vishnu", 35, null);
		when(cdao.save(course)).thenReturn(course);
		assertTrue(service.newCourse(course).getCid()==id);
	}
	
	@Test
	public void updateStudentTest() {
		List<Integer> courseList=new ArrayList<>();
		List<Course> list=new ArrayList<>();
		courseList.add(1);
		StudentDto studentDto=new StudentDto(222, "Anand", "Salem", "+919824356527", 8.7,courseList);
		Course course=new Course(1, "Python", "Vishnu", 35, null);
		list.add(course);
		Student student=new Student(222, "Anand", "Salem", "+919824356527", 8.7,list);
		when(dao.findById(anyInt())).thenReturn(Optional.of(student));
		when(cdao.findById(anyInt())).thenReturn(Optional.of(course));
		when(dao.save(student)).thenReturn(student);
		assertTrue(service.updateStudent(studentDto).getCourses().size()>0);
	}
	
	
	@Test
	public void deletestudent() {
		Optional<Student> student=Optional.of(new Student(222, "Anand", "Salem", "+919824356527", 8.7,null));
		
		when(dao.findById(anyInt())).thenReturn(student);
		service.deletestudent(student.get());
		verify(dao,times(1)).delete(student.get());
	}
	
	
}
