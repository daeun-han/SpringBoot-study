package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 해당 어노테이션 덕분에 객체가 자동으로 생성됨 (스프링 부트)
public class MemberController {

    private final MemberService memberService;

    @Autowired // 생성자 함수에 @Autowired가 붙으면 spring이 springcontainer에 있는 memberserivce를 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
