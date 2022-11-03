package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "username", "age"})
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String username;

  private Integer age;

  @ManyToOne(fetch = FetchType.LAZY) // @ManyToOne & @OneToMany으로 jpaQueryFactory의 innerJoin() 메서드를 사용하지 않아도 조인이 가능하다.
  // 단, selectFrom()으로 Q클래스를 이용하지 않고 Dto를 이용한다면 innerJoin() 메서드를 사용해야 한다.
  @JoinColumn(name = "team_id") // FK설정 
  private Team team;


  public Member(String username) {
    this(username, 0);
  }

  public Member(String username, int age) {
    this(username, age, null);
  }

  public Member(String username, int age, Team team) {
    this.username = username;
    this.age = age;
    if (team != null) {
      changeTeam(team);
    }
  }

  public void changeTeam(Team team) {
    this.team = team;
    team.getMembers().add(this);
  }

}
