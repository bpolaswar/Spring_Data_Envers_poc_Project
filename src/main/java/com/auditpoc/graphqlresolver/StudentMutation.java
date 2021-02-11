package com.auditpoc.graphqlresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auditpoc.entity.Student;
import com.auditpoc.graphql.model.StudentInput;
import com.auditpoc.service.StudentService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class StudentMutation implements GraphQLMutationResolver {
	@Autowired
	private StudentService studentService;

	public Student insertStudent(StudentInput studentInput) {
		return studentService.insertStudent(studentInput);
	}

	public Student updateStudent(StudentInput studentInput) {
		return studentService.updateStudent(studentInput);
	}

	public String deleteStudent(long id) {
		return studentService.deleteStudent(id);
	}
}
