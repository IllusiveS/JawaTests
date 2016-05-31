package com.example.restservicedemo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Book;
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
	@Path("/{bookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBookById(@PathParam("bookId") Long id){
		return bm.getBook(id);
	}

	@POST
	@Path("/{bookName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBookByName(@PathParam("bookName") String name) {
		return bm.getBookByName(name);
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Book book) {
 
		String result = "Book saved: " + book;
		bm.addBook(book);
		return Response.status(201).entity(result).build(); 
	}

}
