package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.entity.Member;
import com.example.demo.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
@Transactional
class QueryDslApplicationTests {


  @Autowired
  EntityManager em;

  JPAQueryFactory queryFactory;

  // QMember qMember = new QMember("m");
  QMember qMember = QMember.member;


  @BeforeEach // JUnit으로 @Test가 붙은 각 메서드가 테스트되기 전 @BeforeEach가 붙은 메서드가 매번 실행된다. 
  // @BeforEach, @AfterEach, @BeforeAll, @AfterAll 등이 있다.
  public void before() {
    this.queryFactory = new JPAQueryFactory(em);
  }


  //  @Test
  //  void contextLoads() {
  //    EntityTest et = new EntityTest();
  //    em.persist(et);
  //
  //    JPAQueryFactory query = new JPAQueryFactory(em);
  //    QEntityTest qEntityTest = QEntityTest.entityTest;
  //
  //    EntityTest result = query.selectFrom(qEntityTest).fetchOne();
  //
  //    assertThat(result).isEqualTo(et);
  //    assertThat(result.getId()).isEqualTo(et.getId());
  //  }
  //
  //  @Test
  //  public void startJPQL() {
  //    String jpqlString = "select m from Member m where m.username = :username";
  //    Member findMember = em.createQuery(jpqlString, Member.class)
  //        .setParameter("username", "member1")
  //        .getSingleResult();
  //
  //    assertThat(findMember.getUsername()).isEqualTo("member1");
  //  }
  //
  //  @Test
  //  public void startQuerydsl() {
  //    QMember m = new QMember("m");
  //    Member findMember = queryFactory
  //        .select(m)
  //        .from(m)
  //        .where(m.username.eq("member1"))
  //        .fetchOne();
  //
  //    assertThat(findMember.getUsername()).isEqualTo("member1");
  //  }

  @Test
  public void search() {


    Member findMember = queryFactory
        .selectFrom(qMember)
        .where(qMember.username.eq("member1")
            .and(qMember.age.eq(100)))
        .fetchOne();

    assertThat(findMember.getUsername()).isEqualTo("member1");


    //    List<Member> result = queryFactory
    //        .selectFrom(qMember)
    //        .where(
    //            qMember.username.eq("member1"),
    //            qMember.age.eq(10)
    //            )
    //        .fetch();
    //
    //    //    System.out.println("result >>>>>>>>>>>>> " + result);
    //
    //    assertThat(result.size()).isEqualTo(1);




  }



  //  @Test
  //  public void sort() {
  //    //    em.persist(new Member(null, 100));
  //    //    em.persist(new Member("memeber5", 100));
  //    //    em.persist(new Member("member6", 100));
  //
  //    //    List<Member> result = queryFactory
  //    //        .selectFrom(member)
  //    //        .where(member.age.eq(100))
  //    //        .orderBy(member.age.desc(), member.username.asc().nullsLast())
  //    //        ,fetch();
  //  }
  //
}
