package com.auditpoc.graphql.model;

import com.auditpoc.entity.Student;

import lombok.Data;

@Data
public class StudentVersionInfo {
	private Student student;
	private String version;
	private String revisionType;
	private String date;
//	private Long revType;
}
