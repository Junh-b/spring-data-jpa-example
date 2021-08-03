package net.junhabaek.spring_data_jpa_example.inheritance_domain.repository;

import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.*;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({AuthorRepositoryImpl.class, ContentRepositoryImpl.class})
public class AuthorRepositoryTest extends RepositoryTestTemplate {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ContentRepository contentRepository;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    @EnabledIf(expression = "#{environment['test.init_with_sql'] == false")
    void initContents(){

        // create Author
        Author drawingAuthor = new Author();
        drawingAuthor.setName("picasso");
        entityManager.persist(drawingAuthor);

        Author storyAuthor = new Author();
        storyAuthor.setName("Shakespeare");
        entityManager.persist(storyAuthor);

        // create Webtoon
        Webtoon webtoon = new Webtoon();
        webtoon.setDrawingAuthor(drawingAuthor);
        webtoon.setStoryAuthor(storyAuthor);
        webtoon.setName("webtoon1");

        WebtoonEpisode episode1 = new WebtoonEpisode();
        episode1.setName("episode1");
        episode1.setImageURL("https://tech.junhabaek.net");

        webtoon.addEpisode(episode1);

        entityManager.persist(webtoon);

        // create Novel
        Novel novel = new Novel();
        novel.setAuthor(storyAuthor);
        novel.setName("novel1");

        entityManager.persist(novel);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void FindAuthor_HaveContents() {
        //given
        String authorName ="Shakespeare";
        List<String> contentNames = Arrays.asList("webtoon1", "novel1");
        Boolean expectedMatchingResult = true;

        //when
        Author author = authorRepository.findAuthorsByName(authorName).get(0);

        //then
        Boolean matchingResult = author.getContents().stream()
                .allMatch(content -> contentNames.contains(content.getName()));

        assertEquals(2, author.getContents().size());
        assertEquals(expectedMatchingResult, matchingResult);
    }
}
