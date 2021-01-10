package com.college.register.services.impl;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.college.register.StudentRepository;
import com.college.register.model.Student;
import com.college.register.services.StudentService;

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import jakarta.nosql.mapping.document.DocumentTemplate;

/**
 * @author Perceive Chuchu
 *
 */
@ApplicationScoped
public class StudentServiceImpl implements StudentService {

  @Inject
  @Database(value = DatabaseType.DOCUMENT, provider = "student-database")
  private StudentRepository repository;
  
  @Inject
  @Database(value = DatabaseType.DOCUMENT, provider = "student-database")
  private DocumentTemplate template;
  
  private static final String DOCUMENT_COLLECTION = "Students";


  @Override
  public Student createStudent(Student student) {
    return repository.save(student);
  }

  @Override
  public Student searchStudent(String studentNumber){
	  DocumentQuery query = DocumentQuery.select().from(DOCUMENT_COLLECTION).where("studentNumber").eq(studentNumber).build();
	  Optional<Student> student = template.singleResult(query);
	  if(student.isPresent()) {
		  return student.get();
	  } else {
		 throw new RuntimeException("Student not found") ;
	  }
  }
}