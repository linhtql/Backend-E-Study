package com.estudy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudy.entities.ResponseObject;
import com.estudy.form.CourseForm;
import com.estudy.model.CourseInfo;
import com.estudy.model.UserInfo;
import com.estudy.repository.CourseRepository;
import com.estudy.service.ICourseService;
import com.estudy.service.IUserService;

@RestController
@RequestMapping(path = "/api/v1/course")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    IUserService iUserService;

    @Autowired
    ICourseService courseService;

    @GetMapping()
    ResponseEntity<ResponseObject> getCourses() {
        ArrayList<CourseInfo> courseInfos = (ArrayList<CourseInfo>) this.courseService.getCourses();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Get courses successfully", courseInfos));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getOneCourse(@PathVariable Long id) {

        CourseInfo courseInfo = this.courseService.getOneCourse(id);
        if (courseInfo != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Get course successfully", courseInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Get course failed !", null));
        }
    }

    @PostMapping()
    ResponseEntity<ResponseObject> createCourse(@ModelAttribute CourseForm courseForm) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        UserInfo user = this.iUserService.getByUserName(username);
        courseForm.setUser(user);

        CourseInfo courseInfo = this.courseService.createCourse(courseForm);
        if (courseInfo != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Create category successfully", courseInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Create category failed !", "Category already exists"));
        }

    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCourse(@PathVariable Long id, @ModelAttribute CourseForm courseForm) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        UserInfo user = this.iUserService.getByUserName(username);
        courseForm.setUser(user);

        CourseInfo courseInfo = this.courseService.updateCourse(courseForm, id);

        if (courseInfo != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Update course successfully", courseInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Update course failed !", "Course is not found !"));
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCourse(@PathVariable Long id) {
        Boolean isDeleted = this.courseService.deleteCourse(id);

        if (isDeleted == true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Delete course successfully", true));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("failed", "Delete course failed !", false));
        }
    }

}
