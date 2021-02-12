package com.auditpoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.auditpoc.entity.Student;
@Repository
public interface StudentRepository extends RevisionRepository<Student, Long, Long>, 
JpaRepository<Student, Long>{

//	@Audited
//	@Transactional
//	@Modifying(flushAutomatically = true, clearAutomatically = true)
//	@Query(nativeQuery = true, value= "update student set active=false where id =:id")
//	int deleteStudent(@Param("id") long id);
	}
