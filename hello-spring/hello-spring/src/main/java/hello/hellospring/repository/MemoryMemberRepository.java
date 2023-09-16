package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence는 0,1,2 키 값을 생성해준다.

    // 단축키: alt shift enter 또는 빨간줄 우클릭
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); // store에 저장하면 map에 저장됨.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // optional 사용하여 null 예외처리 느낌. optional 안하면 빨간 줄 뜸.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // loop 돌리기
                .filter(member -> member.getName().equals(name)) // .equals(name)의 name은 파라미터로 넘겨온 name,, 람다 사용
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
