package net.junhabaek.spring_data_jpa_example.inheritance_domain.repository;

import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.Author;
import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.AuthorRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("InheritanceAuthorRepositoryImpl")
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public Author findAuthorById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> findAuthorsByName(String name) {
        return em.createQuery(
                "select a from InheritanceAuthor a " +
                        "left join fetch a.novels left join fetch a.drawingWebtoons left join fetch a.storyWebtoons" +
                        " where a.name=?1", Author.class)
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public void save(Author author) {
        if(author.getId()==null){
            em.persist(author);
        }
        else{
            em.merge(author);
        }
    }
}
