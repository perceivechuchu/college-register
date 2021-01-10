package com.college.register.dto;

import jakarta.nosql.mapping.Column;

/**
 * @author Perceive Chuchu
 *
 */
public class StudentDto {

	@Column("firstName")
	private String firstName;

	@Column("lastName")
	private String lastName;

	@Column("studentNumber")
	private String studentNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
}