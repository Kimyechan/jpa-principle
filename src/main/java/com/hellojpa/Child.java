package com.hellojpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Child {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Parent parent;
}
