package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //url
    public String hello(Model model) {
        model.addAttribute("data", "spring!!"); //data
        return "hello"; //template
    } // 이 메소드는 hello.html에 data 부분을 찾아서 hello!!값을 넣어주겠네
}
