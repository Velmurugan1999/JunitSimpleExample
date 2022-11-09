package com.junit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junit.models.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer>{

}
