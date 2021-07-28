package net.junhabaek.spring_data_jpa_example.repository;

import lombok.RequiredArgsConstructor;
import net.junhabaek.spring_data_jpa_example.domain.Author;
import net.junhabaek.spring_data_jpa_example.domain.AuthorRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author findById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public Author findByIdNotInitiated(Long id) {
        return em.getReference(Author.class, id);
    }

    @Override
    public List<Author> findByName(String name) {
        return em.createQuery("select a from Author a where a.name=?1", Author.class)
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
