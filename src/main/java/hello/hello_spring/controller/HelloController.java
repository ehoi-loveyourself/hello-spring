package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!");
        return "hello"; // 이렇게 리턴하면 viewResolver가 resoures/templates 아래에 있는 hello.html로 찾아서 거기로 model을 보내줌
    }
}