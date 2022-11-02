package com.example.demo.jpql;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;

// @SpringBootTest
@Transactional
public class QuerydslBasicTest {

  @Autowired
  EntityManager em;

  JPAQueryFactory qf;

  @Test
  public void startJPQL() {
    String jpqlString = "select m from Member m whrer m.username = :username";
    Member findMember = em.createQuery(jpqlString, Member.class).setParameter("userName", "member1")
        .getSingleResult();


    System.out.println(findMember.getUserName());
    // assertThat(findMember.getUserName()).isEqualTo("member1");
  }

}
