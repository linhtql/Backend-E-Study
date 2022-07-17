package com.estudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "enrolls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enroll extends BaseEntity{


    @Column(name = "user_id")
    private Long userId;

    @Column(name = "course_id")
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable=false, updatable=false, referencedColumnName = "id")
    private Course course;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;


}
