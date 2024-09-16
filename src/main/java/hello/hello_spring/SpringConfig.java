package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.*;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new JpaMemberRepository(em);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JdbcMemberRepository(dataSource);
////        return new MemoryMemberRepository();
//        // @Repository 어노테이션을 사용하지 않고, 이렇게 java 코드로 스프링 빈을 등록해주면 나중에 repository 교체가 필요할 때 손 쉽게 바꿀 수 있다.
//        // return new DbMemberRepository();
//    }
}