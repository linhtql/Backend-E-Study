package com.estudy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudy.convert.LessonConvert;
import com.estudy.entities.Chapter;
import com.estudy.entities.Lesson;
import com.estudy.form.LessonForm;
import com.estudy.model.LessonInfor;
import com.estudy.repository.LessionRepository;
import com.estudy.service.ILessonService;

@Service
public class LessonService implements ILessonService {

	@Autowired
	LessionRepository lessonRepository;

	@Autowired
	LessonConvert lessonConvert;

	@Autowired
	StorageService storageService;

	@Override
	public LessonInfor create(LessonForm lessonForm) {
		try {
			Lesson lessonOld = lessonRepository.findOneByName(lessonForm.getName());
			LessonInfor lessonInfo = new LessonInfor();

			if (lessonOld == null) {
				lessonOld = lessonConvert.toEntity(lessonForm);
				lessonOld.setCreatedDate(new Date());
				lessonOld.setAttachment(storageService.storageFile(lessonForm.getAttachment()));

				Lesson lesson = lessonRepository.save(lessonOld);

				return lessonConvert.toModel(lesson);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public LessonInfor update(LessonForm lessonForm, Long id) {
		try {
			Lesson lessonOld = lessonRepository.findOneById(id);
			if (lessonOld != null) {
				Lesson lesson1 = lessonConvert.toEntity(lessonOld, lessonForm);
				if (lessonForm.getAttachment().isEmpty()) {
					lesson1.setAttachment(lesson1.getAttachment());
				} else {
					lesson1.setAttachment(storageService.storageFile(lessonForm.getAttachment()));
				}

				lesson1 = lessonRepository.save(lesson1);

				return lessonConvert.toModel(lesson1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LessonInfor delete(Long id) {
		try {
			Lesson lesson = lessonRepository.findOneById(id);

			if (lesson != null) {
				lessonRepository.deleteById(id);

				return lessonConvert.toModel(lesson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LessonInfor> getAllLessonByIdChapter(Long chapter_id) {
		try {
			List<Lesson> list_lesson = lessonRepository.findByChapterId(chapter_id);

			return lessonConvert.toListModel(list_lesson);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LessonInfor getLessonById(Long id) {
		return lessonConvert.toModel(lessonRepository.findOneById(id));
	}

}
