package com.auditpoc.graphqlresolver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auditpoc.entity.Student;
import com.auditpoc.graphql.model.StudentVersionInfo;
import com.auditpoc.service.StudentService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class StudentQuery implements GraphQLQueryResolver {
	@Autowired
	private StudentService studentService;

	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	public Student getInfo(long id) {
		Student stud = studentService.getInfo(id).getEntity();
		return stud;
	}

	public List<StudentVersionInfo> getAllRevisionInfo(long id) {
		return studentService.getAllRevisionInfo(id);
	}
}
