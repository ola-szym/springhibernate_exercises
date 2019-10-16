package com.example.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
//    Book findByAuthor(String author);
//    Book findByAuthorAndName(String author, String name);
    Book findByNameStartingWith(String name);
    Book findByDate(LocalDate date);
    List<Book> findByDateAfter(LocalDate date);
    void deleteById(Long id);
//    void update(Book book);
}
