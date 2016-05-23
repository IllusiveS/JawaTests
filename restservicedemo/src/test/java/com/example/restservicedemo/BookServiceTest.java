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
	public void getCar(){
		get("/car/0").then().assertThat().body("model", equalTo("Corsa"));
		
		Book aBook = get("/car/0").as(Book.class);
		assertThat(aBook.getName(), equalToIgnoringCase("Opel"));
	}
	
	@Test
	public void addCar(){
		
		Book aBook = new Book(2, "Ford", "Fiesta", 2011);
		given().
		       contentType("application/json").
		       body(aBook).
		when().	     
		post("/car/").then().assertThat().statusCode(201).body(containsString("Book saved:"));
	}
	

}
