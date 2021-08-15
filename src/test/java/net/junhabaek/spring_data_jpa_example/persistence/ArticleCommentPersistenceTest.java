package net.junhabaek.spring_data_jpa_example.persistence;

import net.junhabaek.spring_data_jpa_example.persistence.domain.Article;
import net.junhabaek.spring_data_jpa_example.persistence.domain.Comment;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ArticleCommentPersistenceTest extends RepositoryTestTemplate {
    @PersistenceContext
    EntityManager em;

    @Test
    void RemoveArticle_WithComment_ViolateReferenceConstraints() {
        //given
        Article article = Article.builder()
                .content("article").build();

        em.persist(article);

        Comment comment = Comment.builder()
                .content("comment")
                .article(article).build();

        em.persist(comment);
        em.flush();
        em.clear();

        //when
        article = em.find(Article.class, article.getId());
        em.remove(article);

        //then
        Assertions.assertThrows(PersistenceException.class, ()->em.flush());
    }

}
