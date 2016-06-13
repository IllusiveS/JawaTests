package com.example.restservicedemo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Book;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BookManager;

@Path("book")
public class BookRESTService {

	private BookManager bm = new BookManager();

	@DELETE
	public Response clearBooks() {
		bm.clearBooks();
		return Response.status(200).build();
	}

	@GET
	@Path("/{bookName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBookByName(@PathParam("bookName") String name) {
		return bm.getBookByName(name);
	}



	@POST
	@Path("/{bookId}/{personId}")
	public Response borrowBook(@PathParam("bookId") long bookId, @PathParam("personId") long personId) {
		Book book = bm.getBook(bookId);
		Person person = bm.getPerson(personId);
		bm.rentBook(book, person);
		return Response.status(200).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Book book) {
 
		String result = "Book saved: " + book;
		bm.addBook(book);
		return Response.status(201).entity(result).build(); 
	}

}
