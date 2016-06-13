package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Book;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BookManager;

import java.util.LinkedList;
import java.util.List;

@Path("person")
public class PersonRESTService {	
	
	private BookManager pm = new BookManager();

	@GET
	@Path("/{personName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPersonByName(@PathParam("personName") String name){
		return pm.getPersonByName(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){
		pm.addPerson(person);
		return Response.status(201).entity("Person").build(); 
	}

	@GET
	@Path("/borrowed")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBooksRented(Person person) {
//		return pm.getBooksRentedByPerson(person);
		return new LinkedList<Book>();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String test(){
		return "REST API /person is running";
	}
	
	@DELETE
	public Response clearPersons(){
		pm.clearPersons();
		return Response.status(200).build();
	}

}
