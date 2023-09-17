package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // >어노테이션을 통해< spring이 spring container에 넣어주면서 의존성 주입!!
@Transactional // jpa 사용시, service에 필요
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired // @Autowired는 스프링 빈으로 등록 되어 있을 때만 효력있음.
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    // 회원가입 서비스 로직
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms"); // 단축키: sout 하면 됨
        }
    }

    // 같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName()); // optional 덕분에 여러 메소드 사용 가능 (& member객체도 여기에 포함)
//        result.ifPresent(m -> { //m은 member
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

    // 코드 리팩토링 (다음과 같이 권장)
    // 단축키 ctrl alt m 사용해서 method로 만들기
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회 서비스 로직
    public List<Member> findMembers() {

        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

// 단축키 ctrl shift t 사용해서 test 간편하게 만들기..
