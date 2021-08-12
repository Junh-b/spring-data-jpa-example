package net.junhabaek.spring_data_jpa_example.persistence;

import net.junhabaek.spring_data_jpa_example.persistence.domain.Article;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ArticlePersistenceTest extends RepositoryTestTemplate {

    @PersistenceContext
    EntityManager em;

    @Test
    void DetachedArticle_WillBeInserted() {
        //given
        Article article = Article.builder().content("hello").build();

        //when, then
        em.persist(article);

        em.detach(article);
        Article article2 = em.find(Article.class, 1L);
        Assertions.assertEquals("hello", article2.getContent());

        em.flush();
        em.clear();

        Article article3 = em.find(Article.class, 1L);
        Assertions.assertEquals("hello", article3.getContent());
    }

    @Test
    void ChangeAttribute_MergedInstance_WontApplied() {
        //given
        String initialContent = "initial";
        Article initialArticle = Article.builder()
                .content(initialContent).build();
        em.persist(initialArticle);

        em.flush();
        Long entityId = initialArticle.getId();
        em.clear();

        //when
        String secondContent = "second";
        Article secondArticle = Article.builder()
                .id(entityId).content(secondContent).build();
        em.merge(secondArticle);  // article's message in persistence context will be changed

        //then
        Article foundArticle = em.find(Article.class, entityId);
        Assertions.assertEquals(secondContent, foundArticle.getContent());


        // 원래라면 따로 test Case 분리하는 것을 권장합니다.
        // when2
        String lastContent = "last";
        secondArticle.changeContent(lastContent); // won't affect existing one
        em.flush(); // "second" will be applied
        em.clear();

        // then2
        Article upToDateArticle = em.find(Article.class, entityId);
        Assertions.assertEquals(secondContent, upToDateArticle.getContent());
    }

    @Test
    void MergeArticle_NotPersisted_WillPersisted() {
        //given
        String content = "hello2";
        Article articleForUpdate = Article.builder()
                .id(1L).content(content).build();

        //when
        em.merge(articleForUpdate);
        em.flush();
        em.clear();

        Article persistedArticle = em.find(Article.class, 1L);

        //then
        Assertions.assertNotNull(persistedArticle.getId());
        Assertions.assertEquals(content, persistedArticle.getContent());
    }

    @Test
    void MergeArticle_NotPersisted_WillCreatedWithAutoIncrementId() {
        //given
        String content = "hello";
        Article article = Article.builder()
                .id(2L).content(content).build();

        //when
        em.merge(article);
        em.flush();
        em.clear();

        //then
        Article persistedArticle = em.find(Article.class, 1L);
        Assertions.assertNotEquals(article.getId(), persistedArticle.getId());
    }
}
