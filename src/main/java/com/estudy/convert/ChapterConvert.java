package com.estudy.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudy.entities.Chapter;
import com.estudy.form.ChapterForm;
import com.estudy.model.ChapterInfo;

@Component
public class ChapterConvert {

	@Autowired
	LessionConvert lessionConvert;

	public Chapter toEntity(ChapterForm chapterForm) {
		Chapter chapter = new Chapter();

		chapter.setName(chapterForm.getName());
		chapter.setDescription(chapterForm.getDescription());
		chapter.setCourseId(chapterForm.getCourse_id());

		return chapter;
	}

	public ChapterInfo toModel(Chapter chapter) {
		ChapterInfo chapterInfo = new ChapterInfo();

		chapterInfo.setId(chapter.getId());
		chapterInfo.setName(chapter.getName());
		chapterInfo.setDescription(chapter.getDescription());
		chapterInfo.setCreateAt(chapter.getCreatedDate());
		chapterInfo.setCourse_id(chapter.getCourseId());

		return chapterInfo;
	}

	public Chapter toEntity(Chapter chapterOld, ChapterForm chapterForm) {
		chapterOld.setName(chapterForm.getName() == "" ? chapterOld.getName() : chapterForm.getName());
		chapterOld.setDescription(
				chapterForm.getDescription() == "" ? chapterOld.getDescription() : chapterForm.getDescription());

		return chapterOld;
	}

	public List<ChapterInfo> toListModel(List<Chapter> chapters) {
		List<ChapterInfo> list_chapter_info = new ArrayList<ChapterInfo>();

		for (Chapter chapter : chapters) {
			ChapterInfo chapterInfo = toModel(chapter);
			chapterInfo.setList_lession(lessionConvert.toListModel(chapter.getLessons()));
			list_chapter_info.add(chapterInfo);
		}

		return list_chapter_info;

	}
}
