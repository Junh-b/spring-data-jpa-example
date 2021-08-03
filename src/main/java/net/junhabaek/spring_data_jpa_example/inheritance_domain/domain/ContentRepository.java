package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import java.util.List;

public interface ContentRepository {
    Webtoon findWebtoonById(Long webtoonId);
    List<Webtoon> findWebtoonByName(String webtoonName);
    List<Content> findAllContents();
}
