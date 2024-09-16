package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootApplication // 스프링 컨테이너와 테스트를 함께 실행한다
@Transactional // 테스트 시작 전에 트랜잭션을 실행하고, 테스트 종료후에 롤백을 하기 때문에, DB에 영향없이 테스트를 진행할 수 있다.
public class MemberServiceIntegrationTest {

    private MemberService service;
    private MemoryMemberRepository repository;

    /*
    MemberService service = new MemberService();
    MemoryMemberRepository repository = new MemoryMemberRepository(); // memberService에 내부에서도 repository를 새로 new하고 있는데
    // 여기서 새로 new를 또 하니까 서로 다른 객체가 생성되고 있는 것이므로 문제가 있음
     */

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    @Commit
    public void 회원가입_성공() {
        // given
        Member member = new Member();
        member.setName("엄마");

        // when
        Long memberId = service.join(member);

        // then
        Member result = repository.findById(memberId).get();
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    public void 중복회원가입_실패() {
        // given
        Member member1 = new Member();
        member1.setName("태이");

        Member member2 = new Member();
        member2.setName("태이");

        // when
        service.join(member1);

        // 같은 형태의 에러가 던져지는지 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        // 같은 에러 메시지 내용이 나오는지도 확인
        assertThat(e.getMessage()).isEqualTo("이미 회원이 존재합니다.");

        /*
        // 1번 방법
        try {
            service.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 회원이 존재합니다.");
        }
         */
    }
}