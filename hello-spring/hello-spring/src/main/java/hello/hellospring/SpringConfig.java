package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // spring bean에 넣어진 memberRepository를 MemberService에 넣어줌
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 나중에 db연결하면 MemoryMemberRepository를 dbMemberRepository로 바꾸면 됨
    }
}

// 따라서 db가 정해지지 않았을 경우, 컴포넌트 스캔보다 자바 코드로 등록하는 것이 나중에 코드 수정할 때, 더 간편하다.
