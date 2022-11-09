package com.junit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.models.Course;
import com.junit.models.Student;
import com.junit.service.ServiceClass;
import com.junit.service.StudentDto;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentCourseController.class)
public class ControllerTest {
	public Logger logger=LoggerFactory.getLogger("junit");
	@Autowired
	StudentCourseController controller;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	ServiceClass service;
	
	public static Stream<Arguments> insertStudents(){
		return Stream.of(Arguments.of(111,"Anand","Salem"),
				Arguments.of(2222,"Vishnu","CBE"),
				Arguments.of(3232,"Priya","Chennai"),
				Arguments.of(12344,"Kavitha","Salem"));		
	}
	
	@BeforeEach
	public void start() {
		logger.info("Test started--------->");
	}
	
	@AfterEach
	public void end() {
		logger.info("Test ended--------->");
	}
	
	@Test
	public void allStudentTest() throws Exception {
		Student student=new Student(222, "Anand", "Salem", "+919824356527", 8.7,null);
		List<Student> studentList=new ArrayList<>();
		studentList.add(student);
		when(service.findAllStudents()).thenReturn(studentList);
		this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/studentcourse/allstudent")).andExpect(status().isOk());
	}
	
	@Test
	public void allCourseTest() throws Exception {
		Course course=new Course(22, "Python", "Vishnu", 35, null);
		List<Course> courseList=new ArrayList<>();
		courseList.add(course);
		when(service.findAllCourse()).thenReturn(courseList);
		this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/studentcourse/allcourse"))
						.andExpect(status().isOk());
	}
	
	@ParameterizedTest
	@MethodSource("insertStudents")
	public void insertStudentTest(int id,String name,String city) throws Exception {
		Student student=new Student(id,name, city, "+919824356527", 8.7,null);
		when(service.saveStudent(student)).thenReturn(student);
		this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/studentcourse/student")
						.accept(MediaType.APPLICATION_JSON).content(mapToJson(student))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}
	
	
	@ParameterizedTest
	@ValueSource(ints= {122,3435,56565})
	public void insertCourseTest(int id) throws Exception {
		Course course=new Course(id, "Python", "Vishnu", 35, null);
		when(service.newCourse(course)).thenReturn(course);
		this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/studentcourse/course")
				.accept(MediaType.APPLICATION_JSON).content(mapToJson(course))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@ParameterizedTest
	@ValueSource(ints= {122,3435,56565})
	public void getByIdTest(int id) throws Exception {
		Student student=new Student(id, "Anand", "Salem", "+919824356527", 8.7,null);
		
		when(service.findStudentById(anyInt())).thenReturn(student);
		this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/studentcourse/student/"+id))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studid").value(id));
	}
	
	@ParameterizedTest
	@ValueSource(ints= {122,3435,56565})
	public void getCourseByIDTest(int id) throws Exception {
		Course course=new Course(id, "Python", "Vishnu", 35, null);
		when(service.findCourseById(anyInt())).thenReturn(course);
		this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/studentcourse/course/22"))
											.andExpect(status().isOk());
	}
	
	@Test
	public void updateStudentTest() throws Exception {
		StudentDto student=new StudentDto(222, "Anand", "Salem", "+919824356527", 8.7,null);
		Student updatedstudent=new Student(222, "Anand", "Salem", "+919824356527", 8.7,null);
		when(service.updateStudent(any())).thenReturn(updatedstudent);
		this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/studentcourse/student")
						.accept(MediaType.APPLICATION_JSON).content(mapToJson(student))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.jsonPath("$.studid").exists())
						.andExpect(status().isOk());
	}
	
	@Test
	public void updateCourseTest() throws Exception {
		Course course=new Course(22, "Python", "Vishnu", 35, null);
		logger.info("Test started--------->");
		when(service.updateCourse(any())).thenReturn(course);
		String json=mapToJson(course);
		this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/studentcourse/course")
				.accept(MediaType.APPLICATION_JSON)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cid").exists())
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteStudentTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/studentcourse/student/22"))
				.andExpect(status().isOk());
		verify(service,times(1)).deleteStudent(22);
	}
	
	public String mapToJson(Object object) throws JsonProcessingException
	{
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
}
