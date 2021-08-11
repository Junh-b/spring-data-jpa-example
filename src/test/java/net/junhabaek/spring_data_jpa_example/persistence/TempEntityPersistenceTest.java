package net.junhabaek.spring_data_jpa_example.persistence;

import net.junhabaek.spring_data_jpa_example.persistence.domain.TempEntity;
import net.junhabaek.spring_data_jpa_example.template.RepositoryTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TempEntityPersistenceTest extends RepositoryTestTemplate {

    @PersistenceContext
    EntityManager em;

    @Test
    void FindTempEntity_CheckWhenInsertOccurred() {
        //given
        TempEntity tempEntity = TempEntity.with("hello");

        //when, then
        em.persist(tempEntity);

        em.detach(tempEntity);
        TempEntity tempEntity2 = em.find(TempEntity.class, 1L);
        Assertions.assertEquals("hello", tempEntity2.getMessage());

        em.flush();
        em.clear();

        TempEntity tempEntity3 = em.find(TempEntity.class, 1L);
        Assertions.assertEquals("hello", tempEntity3.getMessage());
    }

    @Test
    void ChangeAttribute_MergedInstance_WontApplied() {
        //given
        String initialMessage = "initial";
        TempEntity tempEntity = TempEntity.with(initialMessage);
        em.persist(tempEntity);

        em.flush();
        Long entityId = tempEntity.getId();
        em.clear();

        //when
        String secondMessage = "second";
        TempEntity secondEntity = TempEntity.with(entityId, secondMessage);
        em.merge(secondEntity);  // tempEntity's message in persistence context will be changed

        //then
        String lastMessage = "last";
        TempEntity foundEntity = em.find(TempEntity.class, entityId);
        Assertions.assertEquals(secondMessage, foundEntity.getMessage());


        // 원래라면 따로 test Case 분리하는 것을 권장합니다.
        // when2
        secondEntity.changeMessage(lastMessage); // won't affect existing one
        em.flush(); // "second" will be applied
        em.clear();

        // then2
        TempEntity upToDateEntity = em.find(TempEntity.class, entityId);
        Assertions.assertEquals(secondMessage, upToDateEntity.getMessage());
    }
}
