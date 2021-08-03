package net.junhabaek.spring_data_jpa_example.inheritance_domain.repository;

import net.junhabaek.spring_data_jpa_example.inheritance_domain.domain.*;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(ContentRepositoryImpl.class)
public class ContentRepositoryTest extends RepositoryTestTemplate {

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
    }

    @Test
    void FindWebtoon_HaveDrawingAuthor() {
        //given
        String webtoonName = "webtoon1";
        String expectedDrawingAuthorName = "picasso";

        //when
        List<Webtoon> webtoons = contentRepository.findWebtoonByName(webtoonName);
//        System.out.println("result size: "+webtoons.size());
        Webtoon webtoon = webtoons.get(0);

        //then
        assertEquals(webtoon.getDrawingAuthor().getName(), expectedDrawingAuthorName);
    }

    @Test
    void FindWebtoon_HaveWebtoonEpisodes() {
        //given
        String webtoonName = "webtoon1";
        String expectedEpisodeName = "episode1";
        String expectedImageURL = "https://tech.junhabaek.net";

        //when
        List<Webtoon> webtoons = contentRepository.findWebtoonByName(webtoonName);
//        System.out.println("result size: "+webtoons.size());
        Webtoon webtoon = webtoons.get(0);
        WebtoonEpisode episode = (WebtoonEpisode) webtoon.getEpisodes().get(0);

        //then
        assertEquals(episode.getName(), expectedEpisodeName);
        assertEquals(episode.getImageURL(), expectedImageURL);
    }

    @Test
    void FindContents_ConsistOfWebtoonsAndNovels() {
        //given
        List<String> contentNames = Arrays.asList("webtoon1", "novel1");
        Boolean expectedMatchingResult = true;

        //when
        List<Content> contents = contentRepository.findAllContents();
//        System.out.println("length: "+contents.size());

        //then
        Boolean matchingResult = contents.stream()
                .allMatch(content -> contentNames.contains(content.getName()));

        contents.forEach(content-> System.out.println(content.getClass()));

        assertEquals(expectedMatchingResult, matchingResult);
    }
}
