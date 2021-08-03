package net.junhabaek.spring_data_jpa_example.inheritance_domain.domain;

import java.util.List;

public interface AuthorRepository {
    Author findAuthorById(Long id);
    List<Author> findAuthorsByName(String name);
}
