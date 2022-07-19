package com.estudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends BaseEntity{


    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "attachment")
    private String attachment;

    @Column(name = "chapter_id")
    private Long chapterId;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "chapter_id", insertable=false, updatable=false, referencedColumnName = "id")
    private Chapter chapter;



}
