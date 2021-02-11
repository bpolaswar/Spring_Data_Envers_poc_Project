package com.auditpoc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Audited
public class Student {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private double marks;
	private boolean active;
}
