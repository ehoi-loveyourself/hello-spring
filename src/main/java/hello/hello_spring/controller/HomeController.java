package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // default 주소 -> Controller 에서 "/"로 매핑되는 게 있으면 리턴해주는 {주소}.html
    public String home() {
        return "home";
    }
}