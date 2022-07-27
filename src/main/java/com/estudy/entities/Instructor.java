package com.estudy.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "instructors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends BaseEntity {

    @Column(name = "user_id", unique = true)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private List<Course> courses;

}
