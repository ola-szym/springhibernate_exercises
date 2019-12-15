//package com.example;
//
//import com.example.book.Author;
//import com.example.book.AuthorRepository;
//import com.example.book.Book;
//import com.example.book.BookRepository;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.util.List;
//import java.time.LocalDate;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BookTest {
//
//	@Autowired
//	BookRepository bookRepository;
//
//	@Autowired
//	AuthorRepository authorRepository;
//
//	@Autowired
//	EntityManager em;
//
//	@Test
//	@Transactional
//	// autor musi byc zapisany jako pierwszy, albo musimy ustawic kaskade na zapisywanie
//	public void shouldFindBook_byAuthorAndName() {
//		//given
//		Author author = new Author();
//		author.setName("Jan");
//		author.setLastName("Kowalski");
//
//		Book book = new Book();
//		book.setName("ksiazka1");
//		book.setAuthor(author);
//		bookRepository.save(book);
//
//		//when
//		Book foundBook = bookRepository.findByName("ksiazka1");
//
//		//then
//		Assert.assertNotNull(foundBook);
//	}
//
//	@Test
//	@Transactional
//	public void shouldFindAuthorsBooks_byAuthorName() {
//		//given
//		Author author = new Author();
//		author.setName("Jan");
//		author.setLastName("Kowalski");
//
//		Book book = new Book();
//		book.setName("ksiazka1");
//		book.setAuthor(author);
//		bookRepository.save(book);
//
//		//when
//		Author foundAuthor = authorRepository.findByName("Jan");
//
//		//then
//		Assert.assertNotNull(foundAuthor.getBooks());
//	}
//
//	@Test
//	@Transactional
//	public void shouldFindBook_byFirstLettersOfName() {
//		//given
//		Book book = new Book();
//		book.setName("ksiazka1");
//		bookRepository.save(book);
//
//		//when
//		Book foundBookByFirstLettersOfName = bookRepository.findByNameStartingWith("ks");
//
//		//then
//		Assert.assertNotNull(foundBookByFirstLettersOfName);
//	}
//
//	@Test
//	@Transactional
//	public void shouldFindAllBooksIssued_afterDate() {
//		//given
//		Book book1 = new Book();
//		book1.setName("ksiazka1");
//		book1.setDate(LocalDate.of(2004,4,12));
//		bookRepository.save(book1);
//
//		Book book2 = new Book();
//		book2.setName("ksiazka2");
//		book2.setDate(LocalDate.of(2017,5,7));
//		bookRepository.save(book2);
//
//
//		//when
//		List<Book> foundAllBooksIssuedAfterDate = bookRepository.findByDateAfter(LocalDate.of(2001,1,1));
//
//		//then
//		Assert.assertEquals(2, foundAllBooksIssuedAfterDate.size());
//	}
//
//	@Ignore
//	@Test
//	@Transactional
//	public void shouldDeleteBook_byId() {
//		//given
//		Book book1 = new Book();
//		book1.setName("ksiazka1");
//		bookRepository.save(book1);
//		em.flush(); // synchronizacja z baza danych
//
//		//when
//		bookRepository.deleteById(1L);
//
//		//then
//		Assert.assertFalse(bookRepository.findById(1L).isPresent());
//	}
//
//	@Test
//	@Transactional
//	public void shouldUpdateDate() {
//		//given
//		Book book1 = new Book();
//		book1.setName("ksiazka1");
//		book1.setDate(LocalDate.of(2000,01,01));
//		bookRepository.save(book1);
//
//		//when
//		book1.setDate(LocalDate.of(2002,02,02));
//		bookRepository.save(book1);
//
//		//then
//		Assert.assertNotNull(bookRepository.findByDate(LocalDate.of(2002,02,02)));
//	}
//
//}
