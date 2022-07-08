package com.estudy.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Course() {
    }

    public Course(String name, String description, double price, double sale_percent, String short_desc, String imageUrl, Long categoryId, Long instructorId, Category category, List<Chapter> chapters, List<Comment> comments, List<Enroll> enrolls, Instructor instructor) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sale_percent = sale_percent;
        this.short_desc = short_desc;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.instructorId = instructorId;
        this.category = category;
        this.chapters = chapters;
        this.comments = comments;
        this.enrolls = enrolls;
        this.instructor = instructor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale_percent() {
        return sale_percent;
    }

    public void setSale_percent(double sale_percent) {
        this.sale_percent = sale_percent;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Enroll> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(List<Enroll> enrolls) {
        this.enrolls = enrolls;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
