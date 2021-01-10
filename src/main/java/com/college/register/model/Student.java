package com.college.register.model;

import org.bson.types.ObjectId;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

/**
 * @author Perceive Chuchu
 *
 */
@Entity("Students")
public class Student {

	@Id("_id")
	private ObjectId id;

	@Column("firstName")
	private String firstName;

	@Column("lastName")
	private String lastName;

	@Column("studentNumber")
	private String studentNumber;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

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