package com.college.register.services;

import java.util.List;

import com.college.register.dto.StudentSearchParams;
import com.college.register.model.Student;

/**
 * @author Perceive Chuchu
 *
 */
public interface StudentService {

	public Student createStudent(Student student);
	
	public Student searchStudent(String studentNumber);

	public List<Student> searchStudents(StudentSearchParams criteria);
}
 