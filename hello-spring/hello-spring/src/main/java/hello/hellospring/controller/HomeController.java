package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 컨트롤러가 정적 파일보다 우선순위가 높다.
    public String home() {
        return "home"; // 따라서, index.html이 아닌 home.html이 return 됨.
    }
}
