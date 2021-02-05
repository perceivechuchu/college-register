package com.college.register.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;

import com.college.register.dto.StudentDto;
import com.college.register.model.Student;
import com.college.register.services.StudentService;

/**
 * @author Perceive Chuchu
 *
 */
@Path("students")
public class StudentResource {

	@Inject
    private StudentService studentService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student searchStudent(@QueryParam("studentNumber") String studentNumber) {
        return studentService.searchStudent(studentNumber);
    }
    
    @POST
    @Produces("application/json")
    public Student createStudent(@RequestBody(name = "student", content = @Content(mediaType = "application/json", 
    	    schema = @Schema(implementation = StudentDto.class)), required = true, description = "create student") StudentDto student) {
        return studentService.createStudent(modelMapper.map(student, Student.class));
    }

}
