package com.estudy.model;

import java.util.Date;

import com.estudy.entities.Course;
import com.estudy.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {
	private Long id;
	private Long userId;
	private Long courseId;
	private String content;
	private Double start;
	private Date createAt;
	private UserInfo user;
}
