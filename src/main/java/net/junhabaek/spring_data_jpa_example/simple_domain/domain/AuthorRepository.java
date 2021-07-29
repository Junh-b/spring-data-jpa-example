package net.junhabaek.spring_data_jpa_example.simple_domain.domain;

import java.util.List;

public interface AuthorRepository {
    public Author findById(Long id);
    public Author findByIdNotInitiated(Long id);
    public List<Author> findByName(String name);
    public List<Author> findAll();
    public void save(Author author);
}
