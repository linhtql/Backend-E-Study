package com.estudy.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.estudy.entities.Chapter;
import com.estudy.entities.Lesson;
import com.estudy.form.ChapterForm;
import com.estudy.form.LessonForm;
import com.estudy.model.ChapterInfo;
import com.estudy.model.LessonInfor;

@Component
public class LessonConvert {

	public Lesson toEntity(LessonForm lessionForm) {
		Lesson lesson = new Lesson();

		lesson.setName(lessionForm.getName());
		lesson.setContent(lessionForm.getContent());
		lesson.setChapterId(lessionForm.getChapter_id());

		return lesson;
	}

	public LessonInfor toModel(Lesson lesson) {
		LessonInfor lessionInfo = new LessonInfor();

		lessionInfo.setId(lesson.getId());
		lessionInfo.setName(lesson.getName());
		lessionInfo.setAttachment(lesson.getAttachment());
		lessionInfo.setContent(lesson.getContent());
		lessionInfo.setChapter_id(lesson.getChapterId());
		lessionInfo.setCreateAt(lesson.getCreatedDate());

		return lessionInfo;
	}

	public List<LessonInfor> toListModel(List<Lesson> lessions) {
		List<LessonInfor> list_lession_info = new ArrayList<LessonInfor>();

		for (Lesson lesson : lessions) {
			LessonInfor lessionInfo = toModel(lesson);
			list_lession_info.add(lessionInfo);
		}

		return list_lession_info;
	}

	public Lesson toEntity(Lesson lessonOld, LessonForm lessonForm) {
		lessonOld.setName(lessonForm.getName() == "" ? lessonOld.getName() : lessonForm.getName());
		lessonForm.setContent(lessonForm.getContent() == "" ? lessonForm.getContent() : lessonForm.getContent());
		lessonForm.setChapter_id(lessonForm.getChapter_id());

		return lessonOld;
	}

}
