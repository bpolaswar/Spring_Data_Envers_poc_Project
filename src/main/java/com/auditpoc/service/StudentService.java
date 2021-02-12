package com.auditpoc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import com.auditpoc.entity.QStudent;
import com.auditpoc.entity.Student;
import com.auditpoc.model.StudentInput;
import com.auditpoc.model.StudentVersionInfo;
import com.auditpoc.repository.StudentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class StudentService {
//	 public static final QStudent student = new QStudent("student");
	@Autowired
	private StudentRepository studentRepository;
	
	@PersistenceContext
	EntityManager entityManager;

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

	@Audited
	@Transactional
	public String deleteStudent(long id) {
		QStudent student = QStudent.student;
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.auditpoc.entity");
//		EntityManager em = emf.createEntityManager();
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//		studentRepository.deleteStudent(id);s
		long del = queryFactory.update(student).where(student.id.eq(id)).set(student.active, false).execute();
//		Student exiStudent = studentRepository.findById(id).get();
//		if(null != exiStudent) {
//			exiStudent.setActive(false);
//			studentRepository.save(exiStudent);
//		}
//		
		return " Record Deleted";
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
