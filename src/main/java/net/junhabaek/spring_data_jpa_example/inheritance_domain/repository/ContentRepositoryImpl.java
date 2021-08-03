package net.junhabaek.spring_data_jpa_example.inheritance_domain.repository;

import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.Content;
import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.ContentRepository;
import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.Webtoon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContentRepositoryImpl implements ContentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Webtoon findWebtoonById(Long webtoonId) {
        return em.find(Webtoon.class, webtoonId);
    }

    @Override
    public List<Webtoon> findWebtoonByName(String webtoonName) {
        return em.createQuery("select w from Webtoon w where w.name=?1",Webtoon.class )
                .setParameter(1, webtoonName)
                .getResultList();
    }

    @Override
    public List<Content> findAllContents() {
        return em.createQuery("select c from Content c", Content.class)
                .getResultList();
    }
}
