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

	@Before
	public void setUpTest() {
		insertTestPerson();
		insertTestBook();
	}

	private void insertTestBook() {
		testBook = new Book();
		testBook.setName("Akademia pana Kleksa");
		testBook.setAuthor("Pisarz książek");
		testBook.setYor(1990);
		manager.addBook(testBook);
	}

	private void insertTestPerson() {
		testPerson = new Person();
		testPerson.setFirstName("Zbigniew");
		testPerson.setYob(1990);
		manager.addPerson(testPerson);
	}

	@After
	public void setDownTest() {
		manager.deleteBook(testBook);
		manager.deletePerson(testPerson);
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
		Person person = manager.getPerson((long) 1);
		Book book = manager.getBook((long) 1);
		manager.rentBook(book, person);

		List<Book> booksFound = manager.getBooksRentedByPerson(person);
		Assert.assertEquals(1, booksFound.size());
	}

	@Test
	public void checkBookReturining() {
		checkBookRenting();
		Book book = manager.getBook((long) 1);
		Assert.assertEquals(1, manager.returnBook(book));
	}
}
