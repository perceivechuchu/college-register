package com.college.register;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import com.college.register.model.Student;

import jakarta.nosql.mapping.Repository;

/**
 * @author Perceive Chuchu
 *
 */
@ApplicationScoped
public interface StudentRepository extends Repository<Student, ObjectId> {
}