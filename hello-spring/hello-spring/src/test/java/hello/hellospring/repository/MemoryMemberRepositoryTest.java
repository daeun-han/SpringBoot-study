package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // ctrl enter하면 static으로 바꿀 수 있음

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test 끝날때마다 repository 초기화, callback 메소드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        println으로 확인하기
//        System.out.println("result = " + (result == member));
//        System.out.println(result);
//        System.out.println(member);

        //assertions 사용해서 확인하기
//        Assertions.assertEquals(member, result); // assertEquals(expected, actual)
        assertThat(member).isEqualTo(result); // 뒤가 expected
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        // 단축키: shift f6하면 rename 한번에 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // get()으로 option 대신함

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        // alt enter 단축키 사용
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
