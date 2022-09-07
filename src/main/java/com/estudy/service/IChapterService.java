package com.estudy.service;

import java.util.List;

import com.estudy.entities.Chapter;
import com.estudy.form.ChapterForm;
import com.estudy.model.ChapterInfo;

public interface IChapterService {
	List<ChapterInfo> getAllChapter();

	ChapterInfo getOneChapter(Long id);

	ChapterInfo create(ChapterForm chapterForm);

	ChapterInfo update(ChapterForm chapterForm, Long id);

	ChapterInfo delete(Long id);
}
