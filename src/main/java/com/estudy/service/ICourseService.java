package com.estudy.service;

import java.util.List;

import com.estudy.form.CourseForm;
import com.estudy.model.CourseInfo;

public interface ICourseService {
    List<CourseInfo> getCourses();

    CourseInfo getOneCourse(Long id);

    CourseInfo createCourse(CourseForm courseForm);

    CourseInfo updateCourse(CourseForm courseForm, Long id);

    Boolean deleteCourse(Long id);
}
