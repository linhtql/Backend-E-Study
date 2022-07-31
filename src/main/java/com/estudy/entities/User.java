package com.estudy.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "birthOfDate")
    private Date birthOfDate;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "avatar", nullable = true)
    private String avatar;

    @Column(name = "role_id")
    private Long roleId = 1L;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Enroll> enrolls;

}
