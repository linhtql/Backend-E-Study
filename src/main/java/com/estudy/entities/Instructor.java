package com.estudy.entities;

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
public class Instructor extends BaseEntity{


    @OneToOne
    @JoinColumn(name ="user_id", insertable=false, updatable=false, referencedColumnName = "id")
    private User user;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private List<Course> courses;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

}
