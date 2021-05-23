package com.example.springvue_demo.account.repository;


import com.example.springvue_demo.account.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//@Repository 어노테이션을 통해서 bean으로 등록되지만, 해당 인터페이스는 자동으로 bean으로 등록이 된다.
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUserName(String id);

}
