package com.example.demo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class QueryDslApplicationTests {


  @Autowired
  EntityManager em;

  JPAQueryFactory queryFactory;

  // [Q-Type 사용하기]
  // QMember qMember = new QMember("m"); // 테이블 alias 설정 
  // -> 조인하는 경우가 아니라면 기본 인스턴스를 사용하여 코드 간결화 하기 
  QMember qMember = QMember.member;


  @BeforeEach 
  // @BeforEach: JUnit으로 @Test가 붙은 각 메서드가 테스트되기 전 @BeforeEach가 붙은 메서드가 매번 실행된다. 
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

    // [select 1: 데이터 하나 조회하기]

    //    Member findMember = queryFactory
    //        .selectFrom(qMember)
    //        .where(qMember.username.eq("member1")
    //            .and(qMember.age.eq(10)))
    //        .fetchOne(); // 하나의 데이터만 조회한다. 결과가 없다면, null을 반환한다. 두 건 이상일 경우 NonUniqueResultException 발생한다.
    //
    //    log.info("test >>>>>>>> " + findMember.getUsername());


    //  [select 2: 여러 데이터 조회하기]

    //    List<Member> result = queryFactory
    //        .selectFrom(qMember)
    //        .where(qMember.username.eq("member1")
    //            .or(qMember.age.eq(10)))
    //        .fetch(); // limit(1).fetch() == fetchFirst()
    //
    //    Iterator<Member> resItr = result.iterator();
    //
    //    while (resItr.hasNext()) {
    //      log.info(">>>>> result >>>>> " + resItr.next());
    //    }
    //
    //    //DB에서 가져온 결과값이 없다면, 빈 배열을 리턴한다.
    //    log.info(">>>>> result >>>>> " + result.toString());

    // 검색조건
    // eq(), ne(), eq().not(), isNotNull(), in(), notIn(), between(), goe(), gt(), loe(), lt(), like(), contains(), startsWith() 등이 있다.



    // [select 4: 데이터 개수 세기]
    //    int result = queryFactory
    //        .selectFrom(qMember)
    //        .where( 
    //            qMember.username.eq("member1")
    //            .or(qMember.age.eq(10))
    //            )
    //        .fetch().size();
    //    // fetchCount()를 사용하지 않는 이유? deprecated
    //    // => fetchResult()의 경우, QueryResults로 count쿼리를 사용하는 것이 완벽하게 지원되지 않기 때문에 fetch()를 사용하여 java에서 List.size() 메서드를 이용할 것을 추천한다.
    //    //    fetchCount()의 경우에는 fetch.size()를 사용할 것을 추천하고 있다. 
    //
    //    log.info(">>>>> result >>>>> " + result);
  }


  @Test
  public void searchWithParam() {
    //     [select 3: where()안에 파라미터로 조건 추가하기] = AND조건

    //    List<Member> result = queryFactory
    //        .selectFrom(qMember)
    //        .where( // 파라미터 값이 null, 즉 아무 조건도 넣지 않으면 selectFrom(qMember).fetch() 와 같은 동작을 한다. => select1보다 깔끔하게 동적 쿼리를 작성할 수 있다.
    //            qMember.username.eq("member1"),
    //            qMember.age.eq(10)
    //            )
    //        .fetch();
    //
    //    Iterator<Member> resItr = result.iterator();
    //
    //    while (resItr.hasNext()) {
    //      log.info(">>>>> result >>>>> " + resItr.next());
    //    }


  }



}
