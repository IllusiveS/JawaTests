package com.example.UnitTests;

import com.example.restservicedemo.domain.Book;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BookManager;
import org.junit.*;

import java.util.List;

/**
 * Created by wysocki on 28.5.2016.
 */
public class BookManagerTests {

	protected BookManager manager = new BookManager();

	protected Book testBook;
	protected Person testPerson;

	@After
	public void setDownTest() {
		manager.clearBooks();
		manager.clearPersons();
	}

	@Test
	public void checkPersonAdding() {
		Person test = new Person();
		test.setFirstName("Zbigniew");
		test.setYob(1990);
		int result = manager.addPerson(test);
		Assert.assertEquals(1, result);
	}

	@Test
	public void checkPersonGetting() {
		Person per = manager.getPerson((long) 1);
		Assert.assertNotEquals(null, per);
	}

	@Test
	public void checkBookRenting() {

		Person person = new Person();
		person.setFirstName("Teodor");
		person.setYob(1981);
		manager.addPerson(person);

		Book book = new Book();
		book.setName("Harry Potter i Kamień RESTowy");
		book.setAuthor("JK Rowling");
		book.setYor(1995);
		manager.addBook(book);

		person = manager.getPersonByName("Teodor");
		book = manager.getBookByName("Harry Potter i Kamień RESTowy");

		Assert.assertEquals(1, manager.rentBook(book, person));

		List<Book> booksFound = manager.getBooksRentedByPerson(person);
		Assert.assertEquals(1, booksFound.size());
	}

	@Test
	public void checkBookReturining() {

		Person person = new Person();
		person.setFirstName("Teodor");
		person.setYob(1981);
		manager.addPerson(person);

		Book book = new Book();
		book.setName("Harry Potter i Kamień RESTowy");
		book.setAuthor("JK Rowling");
		book.setYor(1995);
		manager.addBook(book);

		person = manager.getPersonByName("Teodor");
		book = manager.getBookByName("Harry Potter i Kamień RESTowy");

		Assert.assertEquals(1, manager.rentBook(book, person));

		List<Book> booksFound = manager.getBooksRentedByPerson(person);
		Assert.assertEquals(1, booksFound.size());

		Assert.assertEquals(1, manager.returnBook(book));
		book = manager.getBookByName("Harry Potter i Kamień RESTowy");
		Assert.assertEquals(0, book.getOwner());
	}

	@Test
	public void checkGetBookByName() {
		Book book = new Book();
		book.setName("Harry Potter i komnata Hamcresta");
		book.setAuthor("JK Rowling");
		book.setYor(1890);
		manager.addBook(book);

		Book foundBook = manager.getBookByName("Harry Potter i komnata Hamcresta");
		Assert.assertEquals(book.getAuthor(), foundBook.getAuthor());
		Assert.assertEquals(book.getYor(), foundBook.getYor());
	}
	@Test
	public void checkGetPersonByName() {
		Person person = new Person();
		person.setFirstName("Mietek");
		person.setYob(1891);
		manager.addPerson(person);

		Person foundPerson = manager.getPersonByName("Mietek");
		Assert.assertNotNull(foundPerson);
		Assert.assertEquals(person.getFirstName(), foundPerson.getFirstName());
		Assert.assertEquals(person.getYob(), foundPerson.getYob());
	}
}
