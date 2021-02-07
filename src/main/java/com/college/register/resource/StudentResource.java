package com.college.register.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.modelmapper.ModelMapper;

import com.college.register.dto.StudentDto;
import com.college.register.dto.StudentSearchParams;
import com.college.register.model.Student;
import com.college.register.services.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Perceive Chuchu
 *
 */
@Path("students")
public class StudentResource {

	@Inject
    private StudentService studentService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student searchStudent(@QueryParam("studentNumber") String studentNumber) {
        return studentService.searchStudent(studentNumber);
    }
    
    @GET
	@Operation(summary = "search for students", operationId = "searchStudents")
	@APIResponses(value = {
			@APIResponse(responseCode = "400", description = "bad input", content = @Content(mediaType = "text/plain")),
			@APIResponse(responseCode = "200", description = "searched students successfully", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = Student.class))) })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> searchStudents(@BeanParam StudentSearchParams criteria) {
		return studentService.searchStudents(criteria);
	}
    
    @POST
    @Produces("application/json")
    public Student createStudent(@RequestBody(name = "student", content = @Content(mediaType = "application/json", 
    	    schema = @Schema(implementation = StudentDto.class)), required = true, description = "create student") StudentDto request) throws JsonProcessingException {
        Student student = modelMapper.map(request, Student.class);
        student.setAddress(objectMapper.writeValueAsString(request.getAddress()));
    	return studentService.createStudent(student);
    }

}
