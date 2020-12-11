package com.hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn // DTYPE
public class Item {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
}
