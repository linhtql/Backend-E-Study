package com.estudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity{


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;


    @Column(name = "sale_percent")
    private double sale_percent;

    @Column(name = "short_desc")
    private String short_desc;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "instructor_id")
    private Long instructorId;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable=false, updatable=false, referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Enroll> enrolls;

    @ManyToOne
    @JoinColumn(name = "instructor_id", insertable=false, updatable=false, referencedColumnName = "id")
    private Instructor instructor;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;


}
