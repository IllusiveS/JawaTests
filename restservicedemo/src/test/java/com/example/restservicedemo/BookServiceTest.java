package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import com.example.restservicedemo.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class BookServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}
	
	@Test
	public void getBook(){
		get("/book/1").then().assertThat().body("nazwa", equalTo("Folwark Zwierzęcy"));
		
		Book aBook = get("/book/1").as(Book.class);
		assertThat(aBook.getName(), equalToIgnoringCase("Folwark Zwierzęcy"));
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
