package com.estudy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudy.convert.ChapterConvert;
import com.estudy.convert.LessionConvert;
import com.estudy.entities.Chapter;
import com.estudy.form.ChapterForm;
import com.estudy.model.ChapterInfo;
import com.estudy.repository.ChapterRepository;
import com.estudy.service.IChapterService;

@Service
public class ChapterService implements IChapterService {

	@Autowired
	ChapterRepository chapterRepository;

	@Autowired
	ChapterConvert chapterConvert;

	@Autowired
	LessionConvert lessionConvert;

	@Override
	public ChapterInfo create(ChapterForm chapterForm) {
		try {
			Chapter chapterOld = chapterRepository.findOneByName(chapterForm.getName());
			ChapterInfo chapterInfo = new ChapterInfo();

			if (chapterOld == null) {
				chapterOld = chapterConvert.toEntity(chapterForm);
				chapterOld.setCreatedDate(new Date());

				Chapter chapter = chapterRepository.save(chapterOld);

				chapterInfo = chapterConvert.toModel(chapter);

				return chapterInfo;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public ChapterInfo update(ChapterForm chapterForm, Long id) {
		try {
			Chapter chapterOld = chapterRepository.findOneById(id);

			if (chapterOld != null) {
				Chapter chapter1 = chapterConvert.toEntity(chapterOld, chapterForm);
				chapter1 = chapterRepository.save(chapter1);

				return chapterConvert.toModel(chapter1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ChapterInfo delete(Long id) {
		try {
			Chapter chapter = chapterRepository.findOneById(id);
			if (chapter != null) {
				chapterRepository.deleteById(id);
				return chapterConvert.toModel(chapter);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<ChapterInfo> getAllChapter() {
		List<Chapter> list_chapter = chapterRepository.findAll();
		List<ChapterInfo> list_chapter_info = chapterConvert.toListModel(list_chapter);
		return list_chapter_info;
	}

	@Override
	public ChapterInfo getOneChapter(Long id) {
		Chapter chapter = chapterRepository.findOneById(id);
		ChapterInfo chapter_info = chapterConvert.toModel(chapter);
		chapter_info.setList_lession(lessionConvert.toListModel(chapter.getLessons()));

		return chapter_info;
	}

}
