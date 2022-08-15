package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.datajpa.entity.Member;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {
    @Autowired
    private MemberJpaRepository memberRepository;


    @Test
    void save() {
        Member member = new Member("memberA");
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.find(saveMember.getId());

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    //Optional 로 할 떄는 or else? 이런걸로 하는게 좋다함..

    @Test
    void basicCRUD() {
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        //단건 조회
        Member findMemberA = memberRepository.findById(memberA.getId()).get();
        Member findMemberB = memberRepository.findById(memberB.getId()).get();
        Assertions.assertThat(findMemberA).isEqualTo(memberA);
        Assertions.assertThat(findMemberB).isEqualTo(memberB);

        //카운터 검증
        long count = memberRepository.count();
        Assertions.assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(memberA);
        memberRepository.delete(memberB);

        long deletedCount = memberRepository.count();

        Assertions.assertThat(deletedCount).isEqualTo(0);
    }
}