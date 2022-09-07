package com.estudy.service;

import java.util.List;

import com.estudy.form.LessonForm;
import com.estudy.model.LessonInfor;

public interface ILessonService {

	List<LessonInfor> getAllLessonByIdChapter(Long chapter_id);

	LessonInfor getLessonById(Long id);

	LessonInfor create(LessonForm lessonForm);

	LessonInfor update(LessonForm lessonForm, Long id);

	LessonInfor delete(Long id);
}
