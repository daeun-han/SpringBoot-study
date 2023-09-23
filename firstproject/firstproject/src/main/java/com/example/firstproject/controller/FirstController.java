package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) { // model은 controller 메소드에서 매개변수로 받아옴.
        // 모델에서 변수를 등록할 때, addAttribute() 메소드 사용
        model.addAttribute("username", "daeun"); // username을 "다은"이라는 이름으로 추가
        return "greetings"; // greetings.mustache 파일 반환
    }

    @GetMapping("/bye") // controller가 클라이언트의 요청을 받는 곳
    public String seeYouNext(Model model) { // model 객체 생성 및 받아오기
        model.addAttribute("nickname", "다은"); // 모델 변수 등록하기
        return "goodbye";
    }
}
