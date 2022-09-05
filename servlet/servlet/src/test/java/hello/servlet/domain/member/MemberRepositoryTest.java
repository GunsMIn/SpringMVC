package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.server.reactive.HttpHandlerDecoratorFactory;

import javax.servlet.http.HttpFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    //싱글톤 객체를 가져와주었다
    MemberRepository memberRepository = MemberRepository.getInstance();

    //테스트가 끝날때마다 초기화해주려고
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    public void save() throws Exception {
        //given
        Member member1 = new Member();
        member1.setAge(28);
        member1.setUsername("김건우");

        //when
        Member savedMember = memberRepository.save(member1);

        //then
        Member findMember = memberRepository.findById(member1.getId());
        assertThat(savedMember).isEqualTo(findMember);

    }

    @Test
    public void findAll() throws Exception {
        //given
        Member member1 = new Member("KKIM",12);
        Member member2 = new Member("PARK",13);
        Member member3 = new Member("LEE",145);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //then
        List<Member> all = memberRepository.findAll();

        assertThat(all.size()).isEqualTo(3); // 사이즈 비교
        assertThat(all).contains(member1, member2, member3); //포함 하는가

    }


}