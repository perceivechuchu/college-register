package com.college.register.services;

import com.college.register.model.Student;

/**
 * @author Perceive Chuchu
 *
 */
public interface StudentService {

	public Student createStudent(Student student);
	
	public Student searchStudent(String studentNumber);
}
 