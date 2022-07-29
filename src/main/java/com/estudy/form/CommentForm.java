package com.estudy.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {
	private Long courseId;
	private Long userId;
	private String content;
	private Double star;
}
