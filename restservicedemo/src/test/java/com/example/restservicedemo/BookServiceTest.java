package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.example.restservicedemo.domain.Book;
import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import javax.ws.rs.core.MediaType;

public class BookServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test
	public void removeBooks() {
		delete("/book/").then().assertThat().statusCode(200);
	}

	@Test
	public void getBook(){
		delete("/book/").then().assertThat().statusCode(200);

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
	
//	@Test
//	public void addBook(){
//
//		Book aBook = new Book(2, "Nazwa", "Autor", 2011);
//		aBook.setOwner(1);
//		given().
//		       contentType("application/json").
//		       body(aBook).
//		when().
//		post("/book/").then().assertThat().statusCode(201).body(containsString("Book saved:"));
//	}
	

}
