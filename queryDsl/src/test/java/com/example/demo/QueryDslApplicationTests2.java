package com.example.demo;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.entity.Member;
import com.example.demo.entity.QMember;
import com.example.demo.entity.QTeam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class QueryDslApplicationTests2 {


  @Autowired
  EntityManager em;

  JPAQueryFactory queryFactory;

  QMember member = QMember.member;
  QTeam team = QTeam.team;


  @BeforeEach 
  public void before() {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Test
  public void join() {

    // [inner join 1]

    List<Member> result = queryFactory
        .selectFrom(member)
        .join(member.team, team) // .innerJoin() 도 동일하게 작동한다.
        // => leftJoin(), rightJoin()도 있다.
        .where(team.name.eq("team1"))
        .fetch();

    Iterator<Member> resItr = result.iterator();

    while (resItr.hasNext()) {
      log.info(">>>>> result >>>>> " + resItr.next());
    }

    //    SELECT     *
    //    FROM       member m 
    //    INNER JOIN team t 
    //    ON         m.team_id = t.team_id 
    //    WHERE      t.name = "team1";

  }


}
