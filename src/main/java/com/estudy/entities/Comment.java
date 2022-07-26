package com.estudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

	@Column(name = "user_id")
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false, referencedColumnName = "id")
	private User user;

	@Column(name = "course_id")
	private Long courseId;

	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false, referencedColumnName = "id")
	private Course course;

	@Column(name = "content")
	private String content;

	@Column(name = "star")
	private double star;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "modifiedDate")
	private Date modifiedDate;

}
