package com.estudy.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonInfor {
	private Long id;
	private String name;
	private String content;
	private String attachment;
	private Long chapter_id;
	private Date createAt;
}
