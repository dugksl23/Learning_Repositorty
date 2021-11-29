package com.onboard.dao;

import com.onboard.dto.Member;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@SpringBootTest
public interface MemberRepository extends JpaRepository<Member, Integer> {

    //JpaRepository에서 제공하는 기본 메소드 이외의 query를 위해
    //규칙에 맞는 메서드를 작성.
    // 1. findBy로 시작 - 쿼리를 요청하는 메서드 임을 알림.
    // 2. countBy - 쿼리 결과로 레코드 수를 요청하는 메서드임을 알림.
    // 3. 사용법 - findBy 뒤에 Entity 필드 이름을 입력

    public Member findByEmailAndPassword(String email, String password);

    public Optional<Member> findByEmail(String email);
    // null처리를 위한 Optional

}
