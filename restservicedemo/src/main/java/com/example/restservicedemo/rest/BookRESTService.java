package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Book;
import com.example.restservicedemo.service.BookManager;

@Path("book")
public class BookRESTService {

	private BookManager bm = new BookManager();

	@GET
	@Path("/{bookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getCar(@PathParam("bookId") Long id){
		return bm.getBook(id);
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
