package net.junhabaek.spring_data_jpa_example.domain;

import java.util.List;

public interface AuthorRepository {
    public Author findById(Long id);
    public List<Author> findByName(String name);
    public void save(Author author);
}
