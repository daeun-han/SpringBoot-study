package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // spring bean에 넣어진 memberRepository를 MemberService에 넣어줌
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // 나중에 db연결하면 MemoryMemberRepository를 dbMemberRepository로 바꾸면 됨
//        return new JdbcMemberRepository(dataSource); // 객체지향의 다형성을 활용한다고 볼 수 있음. 인터페이스를 두고 구현체를 바꿔끼기 했기 때문에.
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}

// 따라서 db가 정해지지 않았을 경우, 컴포넌트 스캔보다 자바 코드로 등록하는 것이 나중에 코드 수정할 때, 더 간편하다.
