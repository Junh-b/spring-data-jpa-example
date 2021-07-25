package net.junhabaek.spring_data_jpa_example.repository;

import net.junhabaek.spring_data_jpa_example.domain.Author;
import net.junhabaek.spring_data_jpa_example.domain.AuthorRepository;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryTest extends RepositoryTestTemplate {

    @Autowired
    protected AuthorRepository authorRepository;

    @Test
    void saveAuthorWithoutNovel() {
        //given
        String name = "jun";
        Author author = Author.with(name);

        //when
        authorRepository.save(author);

        //then
        Author foundAuthor = authorRepository.findByName(name).get(0);
    }

    @Test
    void saveAuthorWithoutName() {
        //given
        Author author = Author.with(null);

        //when
        assertThrows(PersistenceException.class, ()->authorRepository.save(author));
    }
}
