package com.junit.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.models.Course;
import com.junit.models.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class RepositoryTest {
	public Logger logger=LoggerFactory.getLogger("junit");
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	StudentDao sDao;
	
	@Autowired
	CourseDao cDao;
	
	@BeforeEach
	public void start() {
		logger.info("Test started--------->");
	}
	
	@AfterEach
	public void end() {
		logger.info("Test ended--------->");
	}
	public static Student studentRef;
	
	public static Course courseRef;
	
	@Test
	@Order(1)
	public void insertStudentTest() {
		Student insertStudent=entityManager.persist(new Student("Anand", "Salem", "+919824356527", 8.7,null));
		
		Student student=sDao.findById(insertStudent.getStudid()).get();
		studentRef=student;
		assertThat(student.getName()).isEqualTo("Anand");
	}
	
	@Test 
	@Order(2)
	public void updateStudentTest() {
		Student insertStudent=entityManager.persist(new Student("Anand", "Salem", "+919824356527", 8.7,null));
		insertStudent.setName("Saran");
		entityManager.merge(insertStudent);
		Student updatedStudent=sDao.findById(insertStudent.getStudid()).get();
		assertThat(updatedStudent.getName()).isEqualTo("Saran");
	}
	
	@Test
	@Order(3)
	public void findAllStudentTest() {
		entityManager.persist(new Student("Anand", "Salem", "+919824356527", 8.7,null));
		
		List<Student> list=sDao.findAll();
		assertTrue(list.size()>0);
	}
	
	@Test
	@Order(4)
	public void deleteStudent() {
		Student insertStudent=entityManager.persist(new Student("Anand", "Salem", "+919824356527", 8.7,null));
		Student student=sDao.findById(insertStudent.getStudid()).get();
		sDao.delete(student);
		assertTrue(sDao.findById(insertStudent.getStudid()).isEmpty());
	}
	
	@Test
	@Order(5)
	public void insertCourseTest() {
		Course course=entityManager.persist(new Course("Python", "Vishnu", 35, null));
		Course getCourse=cDao.findById(course.getCid()).get();
		assertTrue(getCourse.getStaff_name().equals("Vishnu"));
	}
	
	@Test
	@Order(6)
	public void updateCourseTest() {
		Course course=entityManager.persist(new Course("Python", "Vishnu", 35,null));
		
		course.setDuration(50);
		Course updatedCourse=cDao.save(course);
		assertThat(updatedCourse.getDuration()).isEqualTo(50);
	}
	
	@Test
	@Order(7)
	public void findAllCourse() {
		entityManager.persist(new Course("Python", "Vishnu", 35,null));
		
		List<Course> list=cDao.findAll();
		assertTrue(list.size()>0);
	}
	
	@Test
	@Order(8)
	public void deleteCourse() {
		Course course=entityManager.persist(new Course("Python", "Vishnu", 35,null));
		
		cDao.delete(course);
		assertTrue(cDao.findById(course.getCid()).isEmpty());
	}
	
	
}
