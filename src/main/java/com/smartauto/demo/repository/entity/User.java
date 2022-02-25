package com.smartauto.demo.repository.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;

@Entity
@Builder
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String username;

    private String password;

    private LocalDate createdOn;

    private LocalDate modifiedOn;

    private String role;
}
