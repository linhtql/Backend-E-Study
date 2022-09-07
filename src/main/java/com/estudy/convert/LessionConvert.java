package com.estudy.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.estudy.entities.Chapter;
import com.estudy.entities.Lesson;
import com.estudy.model.ChapterInfo;
import com.estudy.model.LessionInfor;

@Component
public class LessionConvert {

	public LessionInfor toModel(Lesson lesson) {
		LessionInfor lessionInfo = new LessionInfor();

		lessionInfo.setId(lesson.getId());
		lessionInfo.setAttachment(lesson.getAttachment());
		lessionInfo.setContent(lesson.getContent());
		lessionInfo.setCreateAt(lesson.getCreatedDate());

		return lessionInfo;
	}

	public List<LessionInfor> toListModel(List<Lesson> lessions) {
		List<LessionInfor> list_lession_info = new ArrayList<LessionInfor>();

		for (Lesson lesson : lessions) {
			LessionInfor lessionInfo = toModel(lesson);
			list_lession_info.add(lessionInfo);
		}

		return list_lession_info;
	}

}
