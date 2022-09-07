package com.estudy.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfo {
    private Long id;
    private String name;
    private String description;
    private double price;
    private double sale_percent;
    private String short_desc;
    private String imageUrl;
    private CategoryInfo category;
    private List<ChapterInfo> chapters;
    private UserInfo instructor;
}
