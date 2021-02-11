package com.auditpoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.auditpoc.entity.Student;

public interface StudentRepository extends RevisionRepository<Student, Long, Long>, JpaRepository<Student, Long> {

	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(nativeQuery = true, value= "update student set active=false where id =:id")
	int deleteStudent(@Param("id") long id);
	}
