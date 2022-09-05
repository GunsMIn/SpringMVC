package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {


    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤으로 객체를 만들어줌
    private static final MemberRepository instance = new MemberRepository();

    //다른 곳에서 싱글톤으로 만들어진 객체를 사용하기위해서
    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){} //싱글톤을 만들어줄 때에는 생성자를 private으로 막아야한다

    //회원 등록(저장)
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        //id로 회원 찾기
        return store.get(id);
    }

    public List<Member> findAll(){
        //회원 목록
        return new ArrayList<>(store.values());
    }

    //테스트에서 사용하기위해서
    public void clearStore(){
        store.clear();
    }
}
