package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello") //url
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //data
        return "hello"; //template
    } // 이 메소드는 hello.html에 data 부분을 찾아서 hello!!값을 넣어주겠네

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name22") String name2, Model model) { //  hello-mvc?name22=spring! 하면, spring!이 name2 값으로 들어오고
        model.addAttribute("name", name2); // 들어온 name2값이 template의 name 값으로 들어가는
        return "hello-template";
    }
}
