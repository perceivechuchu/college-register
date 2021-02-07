package com.college.register.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.college.register.StudentRepository;
import com.college.register.constants.SqlQueries;
import com.college.register.dto.StudentSearchParams;
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

	private static final String AND = "and";

	private static final String SPACE = " ";

	private static final String SQL_QUERY_BASE = "select * from Students where";

	@Override
	public Student createStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public Student searchStudent(String studentNumber) {
		DocumentQuery query = DocumentQuery.select().from(DOCUMENT_COLLECTION).where("studentNumber").eq(studentNumber)
				.build();
		Optional<Student> student = template.singleResult(query);
		if (student.isPresent()) {
			return student.get();
		} else {
			throw new RuntimeException("No student found");
		}
	}

	@Override
	public List<Student> searchStudents(StudentSearchParams criteria) {
		return template.query(buildStudentSearchQuery(criteria)).map(jsonObject -> (Student) jsonObject)
				.collect(Collectors.toList());
	}

	private String buildStudentSearchQuery(StudentSearchParams criteria) {
		StringBuilder queryBulder = new StringBuilder(SQL_QUERY_BASE);
		String query = "";
		if (StringUtils.isNotEmpty(criteria.getCity())) {
			queryBulder.append(populateAddressCondition("city", criteria.getCity()));
		}
		if (StringUtils.isNotEmpty(criteria.getRegion())) {
			queryBulder.append(populateAddressCondition("region", criteria.getRegion()));
		}
		if (StringUtils.isNotEmpty(criteria.getCountry())) {
			queryBulder.append(populateAddressCondition("country", criteria.getCountry()));
		}
		query = queryBulder.toString();
		if (query.endsWith(AND)) {
			query = query.substring(0, query.lastIndexOf(SPACE));
		}
		if (query.equals(SQL_QUERY_BASE)) {
			throw new RuntimeException("No SQL query condition provided");
		}
		return query;
	}

	private String populateAddressCondition(String jsonKey, String jsonValue) {
		return SqlQueries.SQL_QUERY_ADDRESS_CONDITION_TEMPLATE.replace("<jsonKey>", jsonKey).replace("<jsonValue>",
				jsonValue) + AND;
	}
}