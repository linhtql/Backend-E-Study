package com.estudy.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonForm {
	private String name;
	private String content;
	private MultipartFile attachment;
	private Long chapter_id;
}
