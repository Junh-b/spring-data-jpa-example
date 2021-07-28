package net.junhabaek.spring_data_jpa_example.repository;

import net.junhabaek.spring_data_jpa_example.domain.Author;
import net.junhabaek.spring_data_jpa_example.domain.AuthorRepository;
import net.junhabaek.spring_data_jpa_example.domain.Novel;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.PersistenceException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryTest extends RepositoryTestTemplate {

    @Autowired
    protected AuthorRepository authorRepository;

    @Sql("classpath:db/data.sql")
    @Test
    void findAuthorWithoutNovelById() {
        //given
        Long id = 1L;
        String expectedName = "jun";

        //when, then
        Author author = authorRepository.findById(id);
        assertEquals(author.getName(),expectedName);
    }

    @Sql("classpath:db/data.sql")
    @Test
    void findAuthorWithoutNovelByName() {
        //given
        String name = "jun";
        Long expectedId = 1L;

        //when, then
        Author author = authorRepository.findByName(name).get(0);
        assertEquals(author.getId(), expectedId);
    }

    @Sql("classpath:db/dataWithNovel.sql")
    @Test
    void findAuthorWithNovelById() {
        //given
        Long id = 1L;
        String expectedBookName = "tragedy of y";

        //when
        Author author = authorRepository.findById(id);
//        System.out.println(author.getClass().toString());
        List<Novel> novels = author.getNovels();
        Novel novel = novels.get(0); //select novel

        //then
        assertEquals(novel.getName(), expectedBookName);
        System.out.println(novel.getName());
    }

    @Sql("classpath:db/dataWithTwoNovel.sql")
    @Test
    void findAuthorWithTwoNovelById() {
        //given
        Long id = 1L;
        String expectedFirstBookName = "tragedy of y";
        String expectedSecondBookName = "confessions";

        //when
        Author author = authorRepository.findById(id);
        List<Novel> novels = author.getNovels();
        Novel firstNovel = novels.get(0); //select novel
        Novel secondNovel = novels.get(1); //select not occurred

        //then
        assertEquals(firstNovel.getName(), expectedFirstBookName);
        assertEquals(secondNovel.getName(), expectedSecondBookName);
    }

    @Sql("classpath:db/dataWithNovel.sql")
    @Test
    void findAuthorWithNovelByIdNotInitiated() {
        //given
        Long id = 1L;
        String expectedBookName = "tragedy of y";

        //when
        Author author = authorRepository.findByIdNotInitiated(id);
//        System.out.println(author.getClass().toString());

        System.out.println(author.getName());

        List<Novel> novels = author.getNovels();
        Novel novel = novels.get(0);

        //then
        assertEquals(novel.getName(), expectedBookName);
        System.out.println(novel.getName());
    }

    @Test
    void saveAuthorWithoutNovel() {
        //given
        String name = "jun2";
        Author author = Author.with(name);

        //when
        authorRepository.save(author);

        //then
//        Author foundAuthor = authorRepository.findByName(name).get(0);
    }

    @Test
    void saveAuthorWithoutName() {
        //given
        Author author = Author.with(null);

        //when
        assertThrows(PersistenceException.class, ()->authorRepository.save(author));
    }
}
