package com.estudy.model;

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
    private UserInfo instructor;
    private int enrollNumber;
}
