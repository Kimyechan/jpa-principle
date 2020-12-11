package com.hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "products")
//    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    List<MemberProduct> memberProducts = new ArrayList<>();
}
