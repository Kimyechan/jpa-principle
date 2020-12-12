package com.hellojpa;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Member{
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "name")
    private String username;

    /*@Column(name = "TEAM_ID")
    private Long teamId;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    // 기간
    @Embedded
    private Period wordPeriod;

    @Embedded
    private Address homeAddress;

    // 주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address address;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 양 객체에 추가시키는 방법
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
