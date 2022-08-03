package com.estudy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudy.convert.ChapterConvert;
import com.estudy.convert.LessionConvert;
import com.estudy.entities.Category;
import com.estudy.entities.Course;
import com.estudy.entities.Instructor;
import com.estudy.entities.User;
import com.estudy.form.CourseForm;
import com.estudy.model.CategoryInfo;
import com.estudy.model.CourseInfo;
import com.estudy.model.UserInfo;
import com.estudy.repository.CategoryRepository;
import com.estudy.repository.CourseRepository;
import com.estudy.repository.InstructorRepository;
import com.estudy.service.ICourseService;
import com.estudy.service.IStorageService;
import com.estudy.service.IUserService;
import com.estudy.utils.ObjectMapper;

@Service
public class CourseService implements ICourseService {

    @Autowired
    IUserService iUserService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    ChapterConvert chapterConvert;
    @Autowired
    IStorageService iStorageService;

    @Autowired
    ObjectMapper mapper;

    @Override
    public CourseInfo createCourse(CourseForm courseForm) {

        Category category = categoryRepository.findOneById(courseForm.getCategoryId());
        Instructor instructor = instructorRepository.findFirstByUserId(courseForm.getUser().getId());

        Course course = new Course();
        String url = iStorageService.storageFile(courseForm.getImage());

        course.setCategory(category);
        course.setCategoryId(category.getId());
        course.setDescription(courseForm.getDescription());
        course.setInstructor(instructor);
        course.setInstructorId(instructor.getId());
        course.setName(courseForm.getName());
        course.setPrice(courseForm.getPrice());
        course.setSale_percent(courseForm.getSale_percent());
        course.setShort_desc(courseForm.getShort_desc());
        course.setImageUrl(url);

        course.getInstructor().setUser(mapper.map(courseForm.getUser(), User.class));

        Course courseSave = this.courseRepository.save(course);

        return this.convertToCourseInfo(courseSave);

    }

    @Override
    public Boolean deleteCourse(Long id) {
        Course course = courseRepository.findOneById(id);
        if (course == null) {
            return false;
        }
        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CourseInfo> getCourses() {
        ArrayList<Course> courses = (ArrayList<Course>) this.courseRepository.findAll();
        ArrayList<CourseInfo> courseInfos = new ArrayList<>();
        for (Course course : courses) {
            UserInfo userInfo = iUserService.convertToUserInfo(course.getInstructor().getUser());
            course.getInstructor().setUser(mapper.map(userInfo, User.class));
            courseInfos.add(this.convertToCourseInfo(course));
        }
        return courseInfos;
    }

    @Override
    public CourseInfo getOneCourse(Long id) {
        Course course = courseRepository.findOneById(id);
        if (course == null) {
            return null;
        }
        UserInfo userInfo = iUserService.convertToUserInfo(course.getInstructor().getUser());
        course.getInstructor().setUser(mapper.map(userInfo, User.class));

        CourseInfo courseInfo = this.convertToCourseInfo(course);
        courseInfo.setChapters(chapterConvert.toListModel(course.getChapters()));
        return courseInfo;
    }

    @Override
    public CourseInfo updateCourse(CourseForm courseForm, Long id) {

        Course course = courseRepository.findOneById(id);
        if (course == null) {
            return null;
        }
        Category category = categoryRepository.findOneById(courseForm.getCategoryId());

        String url = null;

        if (courseForm.getImage() == null || courseForm.getImage().isEmpty()) {
            url = course.getImageUrl();
        }

        else {
            url = iStorageService.storageFile(courseForm.getImage());
        }

        course.setCategory(category);
        course.setCategoryId(category.getId());
        course.setDescription(courseForm.getDescription());
        course.setName(courseForm.getName());
        course.setPrice(courseForm.getPrice());
        course.setSale_percent(courseForm.getSale_percent());
        course.setShort_desc(courseForm.getShort_desc());
        course.setImageUrl(url);
        course.getInstructor().setUser(mapper.map(courseForm.getUser(), User.class));

        Course courseSave = this.courseRepository.save(course);

        return this.convertToCourseInfo(courseSave);
    }

    public CourseInfo convertToCourseInfo(Course course) {
        CourseInfo courseInfo = new CourseInfo();

        courseInfo.setCategory(mapper.map(course.getCategory(), CategoryInfo.class));
        courseInfo.setDescription(course.getDescription());
        courseInfo.setImageUrl(course.getImageUrl());
        courseInfo.setId(course.getId());
        courseInfo.setInstructor(mapper.map(course.getInstructor().getUser(), UserInfo.class));
        courseInfo.setName(course.getName());
        courseInfo.setPrice(course.getPrice());
        courseInfo.setSale_percent(course.getSale_percent());
        courseInfo.setShort_desc(course.getShort_desc());
        return courseInfo;
    }

}
