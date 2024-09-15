package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!");
        return "hello"; // 이렇게 리턴하면 viewResolver가 resoures/templates 아래에 있는 hello.html로 찾아서 거기로 model을 보내줌
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /* 잘 쓸 일 없음 */
    @GetMapping("hello-spring")
    @ResponseBody // ResponseBody 에 viewResolver 대신에 HttpMessageConverter가 동작함
                    // return 되는 String을 그대로 담아서 보내주겠다.
    public String helloSpring(@RequestParam("name") String name) { // Model이 필요없음
        return "Hello " + name; // 이렇게 문자열을 리턴하게 되면 StringHttpMessageConverter 동작
    }

    @GetMapping("hello-api")
    @ResponseBody // ResponseBody 어노테이션이 들어오면 그냥 return 되는 친구를 걍 넘겨줘야 되겠구나! 하고 생각함
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 리턴하면 JSON 타입으로 리턴한다 -> MappingJackson2HttpMessageConverter
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}