package com.auditpoc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import com.auditpoc.entity.Student;
import com.auditpoc.graphql.model.StudentInput;
import com.auditpoc.graphql.model.StudentVersionInfo;
import com.auditpoc.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public Student insertStudent(StudentInput studentInput) {
		Student student = Student.builder().name(studentInput.getName()).active(true).marks(studentInput.getMarks())
				.build();
		return studentRepository.save(student);
	}

	public Student updateStudent(StudentInput studentInput) {
		Student exiStudent = studentRepository.findById(studentInput.getId()).get();
		if (null != exiStudent) {
			exiStudent.setName(studentInput.getName());
			exiStudent.setMarks(studentInput.getMarks());
			return studentRepository.save(exiStudent);
		}
		return null;
	}

	public String deleteStudent(long id) {
		studentRepository.deleteStudent(id);
		return "Deleted";
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Revision<Long, Student> getInfo(long id) {
		return studentRepository.findLastChangeRevision(id).get();
	}

	public List<StudentVersionInfo> getAllRevisionInfo(long id) {
		List<StudentVersionInfo> result = new ArrayList<>();
		List<Revision<Long, Student>> students = studentRepository.findRevisions(id).toList();
		StudentVersionInfo studentVersionInfo = null;
		int i = 1;
		for (Revision<Long, Student> s : students) {
			studentVersionInfo = new StudentVersionInfo();
			studentVersionInfo.setStudent(s.getEntity());
			studentVersionInfo.setVersion("Version " + i);
			studentVersionInfo.setRevisionType(s.getMetadata().getRevisionType().name());
			String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
					.format(new java.util.Date(s.getMetadata().getRequiredRevisionInstant().getEpochSecond() * 1000));
			studentVersionInfo.setDate(date);
			result.add(studentVersionInfo);
			i++;
		}
		Collections.reverse(result);
		return result;
//		studentRepository.findRevisions(id).get().forEach(x -> {
//			x.getEntity().setEditVersion(x.getMetadata());
//			result.add(x.getEntity());
//		});
//		
//		return result;
	}
}
