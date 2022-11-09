package com.junit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junit.models.Course;


@Repository
public interface CourseDao extends JpaRepository<Course, Integer>{

}
