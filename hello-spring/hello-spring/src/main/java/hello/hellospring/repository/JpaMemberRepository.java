package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa는 모두 EntityManager로 동작

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    } // pk라 jpql 작성 안 해도 됨.

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql이라는 객체지향쿼리 언어를 써야함.
                .getResultList(); // 객체 대상으로 쿼리를 날리면 sql로 번역이 됨.
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
