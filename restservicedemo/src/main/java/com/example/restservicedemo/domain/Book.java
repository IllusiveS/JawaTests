package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	
	private long id;
	private String name;
	private String author;
	private int yor;
	
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
	
}
