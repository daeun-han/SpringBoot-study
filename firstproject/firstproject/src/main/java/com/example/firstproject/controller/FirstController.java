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
}
