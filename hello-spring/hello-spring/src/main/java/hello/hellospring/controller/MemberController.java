package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// controller 파일은 컴포넌트 스캔 방식으로
@Controller // 해당 어노테이션 덕분에 객체가 자동으로 생성됨 (스프링 부트)
public class MemberController {

    private final MemberService memberService;

    // DI 3가지
    // 생성자 주입으로 DI (권장)
    @Autowired // 생성자 함수에 @Autowired가 붙으면 spring이 springcontainer에 있는 memberserivce를 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

        // 실제 Proxy가 주입되는지 콘솔에 출력해서 확인하기
        System.out.println("memberService = " + memberService.getClass()); // memberservice를 가지고 복제해서 코드를 조작하는 기술. (aop에서 사용)
    }

    // 필드 주입으로 DI
//    @Autowired private final MemberService memberService;

    // setter 주입으로 DI
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // GetMapping은 url 입력하면 나오는 거라고 보면 됨. (조회)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // PostMapping은 보통 데이터들을 form에 넣어서 전달할 때 사용.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        console에서 확인해보기
//        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
