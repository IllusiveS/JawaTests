package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import javax.ws.rs.core.MediaType;

import com.example.restservicedemo.domain.Book;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PersonServiceTest {

	private static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;

	private static final String PERSON_FIRST_NAME = "Jasiu";
	
	@BeforeClass
    public static void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";

		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);

		databaseTester = new JdbcDatabaseTester(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
    }

	@Before
	public void setup() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	public void deletePersons() {
		delete("/person/").then().assertThat().statusCode(200);
	}

	@Test
	public void removeBooks() {
		delete("/book/").then().assertThat().statusCode(200);
	}

	@Test
	public void getBook(){

		Book book = new Book(2L, "Folwark Zwierzęcy", "Zbigniew", 1895);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(book).
				when().
				post("/book/").then().assertThat().statusCode(201);
		Book rBook = given().
				contentType(MediaType.APPLICATION_JSON).
				when().
				get("/book/" + "Folwark Zwierzęcy").as(Book.class);

		assertThat(rBook, notNullValue());
		assertThat("Folwark Zwierzęcy", equalToIgnoringCase(rBook.getName()));
	}

	@Test
	public void addPerson() throws Exception {
		
		Person aPerson = new Person("Ziutek", 2010);
		given().contentType(MediaType.APPLICATION_JSON).body(aPerson)
				.when().post("/person/").then().assertThat().statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("PERSON");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"ID"});

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/personData.xml"));
		ITable expectedTable = expectedDataSet.getTable("PERSON");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	@Test
	public void addPersons() throws Exception {

		Person aPerson = new Person("Ziutek", 2010);
		Person bPerson = new Person("Marek", 2011);
		Person cPerson = new Person("Stefan", 1990);
		given().contentType(MediaType.APPLICATION_JSON).body(aPerson)
				.when().post("/person/").then().assertThat().statusCode(201);
		given().contentType(MediaType.APPLICATION_JSON).body(bPerson)
				.when().post("/person/").then().assertThat().statusCode(201);
		given().contentType(MediaType.APPLICATION_JSON).body(cPerson)
				.when().post("/person/").then().assertThat().statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("PERSON");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"ID"});

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/personsData.xml"));
		ITable expectedTable = expectedDataSet.getTable("PERSON");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	@Test
	public void borrowBook() throws Exception {
		given().post("book/0/0/").then().assertThat().statusCode(200);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BOOK");

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/borrowData.xml"));
		ITable expectedTable = expectedDataSet.getTable("BOOK");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	@Test
	public void getBorrowedBooks() throws Exception {
		Person aPerson = new Person(0, "Bolek", 1972);

		List<Book> books = new LinkedList<Book>();

		Book[] bookArray =
				given().
						contentType(MediaType.APPLICATION_JSON).
				when().
						contentType(MediaType.APPLICATION_JSON).get("/person/borrowed/0").as(Book[].class);

		books = Arrays.asList(bookArray);
		Assert.assertEquals(2, books.size());
	}

}
