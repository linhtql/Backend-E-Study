package com.estudy.model;

import java.util.Date;
import java.util.List;

import com.estudy.entities.Lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterInfo {
	private Long id;
	private String name;
	private String description;
	private Long course_id;
	private List<LessionInfor> list_lession;
	private Date createAt;
}
