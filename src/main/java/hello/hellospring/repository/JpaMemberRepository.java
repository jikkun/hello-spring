package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 EntityManager로 모든것을 동작함 Springboot가 알아서 만들어준 EntityManager를 DI 해서 사용함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    
    // PK로 조회하는게 아니면 jpql을 사용하여야함 but Spring data Jpa를 사용하면 jpql을 사용 안해도 되는 경우 존재
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql문, 일반적으로 사용하는 테이블대상으로 쿼리를 날리는게아닌 엔티티를 대상으로 쿼리를 날림
        return em.createQuery("select m from Member m", Member.class)
              .getResultList();
    }
}
