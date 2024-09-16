package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        /*
        repository.findByName(member.getName())
                .ifPresent(m -> new IllegalStateException("이미 회원이 존재합니다.")); // 기존 코드가 이렇게 생겨서 exception 이 던져지지 않고 있엇다.
        */
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 회원이 존재합니다.");
                }); // 기존 코드가 이렇게 생겨서 exception 이 던져지지 않고 있엇다.
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }
}