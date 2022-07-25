package com.estudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "category")
	private List<Course> courses;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "modifiedDate")
	private Date modifiedDate;

}
