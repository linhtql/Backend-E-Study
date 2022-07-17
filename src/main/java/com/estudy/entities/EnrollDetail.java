package com.estudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "enroll_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollDetail extends BaseEntity{


    @Column(name = "date")
    private Date date;

    @Column(name = "payment_method")
    private String payment_method;

    @Column(name = "status")
    private int status;

    @OneToOne
    @JoinColumn(name = "enroll_id")
    private Enroll enroll;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;


}
