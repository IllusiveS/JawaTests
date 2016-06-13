package com.example.restservicedemo.domain;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonAutoDetect
public class Book {

	@JsonProperty("id")
	private long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("author")
	private String author;

	@JsonProperty("yor")
	private int yor;

	private int owner;
	
	public Book(long id, String name, String author, int yor) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.yor = yor;
	}
	
	public Book() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYor() {
		return yor;
	}
	public void setYor(int yor) {
		this.yor = yor;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
}
