package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// controller 파일은 컴포넌트 스캔 방식으로
@Controller // 해당 어노테이션 덕분에 객체가 자동으로 생성됨 (스프링 부트)
public class MemberController {

    private final MemberService memberService;

    // DI 3가지
    // 생성자 주입으로 DI (권장)
    @Autowired // 생성자 함수에 @Autowired가 붙으면 spring이 springcontainer에 있는 memberserivce를 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 필드 주입으로 DI
//    @Autowired private final MemberService memberService;

    // setter 주입으로 DI
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
