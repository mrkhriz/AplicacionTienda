package com.personal.persistence.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @NotNull
    @Column(name = "password", length = 32, nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }
}