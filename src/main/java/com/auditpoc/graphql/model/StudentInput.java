package com.auditpoc.graphql.model;

import lombok.Data;

@Data
public class StudentInput {
	private long id;
	private String name;
	private double marks;
}
