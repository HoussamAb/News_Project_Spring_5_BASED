package com.master4.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Entity
@ToString
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="role_id")
    private List<Role> roles;

    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name="modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Article> articles;

    public User(long id) {
        this.id=id;
    }
}
