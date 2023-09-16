package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody //HTTP의 BODY에 직접 넣어주겠단 의미
    public String helloString(@RequestParam("name22") String name) {
        return "hello " + name; // 따라서 view(template)가 따로 필요 없음!
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 return
        // @ResponseBody와 함께 객체를 return하게 되면 -> json으로 반환하게 됨
    }
    static class Hello { // static이용하면 class 안에 class 사용 가능
        private String name;

        // getter setter 단축키: ctrl + n -> all에서 getter setter 클릭
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
