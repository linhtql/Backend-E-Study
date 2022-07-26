package com.estudy.form;

import org.springframework.web.multipart.MultipartFile;

import com.estudy.model.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseForm {
    private String name;
    private String description;
    private double price;
    private double sale_percent;
    private String short_desc;
    private MultipartFile image;
    private Long categoryId;

    private UserInfo user;
}
