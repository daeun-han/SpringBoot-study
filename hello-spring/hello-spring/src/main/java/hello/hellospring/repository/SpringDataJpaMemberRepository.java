package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface일 때는 extends,,, class는 implements
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // JpaRepository<key, id(pk)의 type>

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
//    Optional<Member> findByNameAndId(String name, Long id);  // 메서드 네임을 수정하여 기능 커스텀 가능
}