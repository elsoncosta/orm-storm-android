package com.example.entity;

import java.io.Serializable;

import com.turbomanage.storm.api.Entity;

@Entity
public class Book implements Serializable
{
	private transient static final long serialVersionUID = -6526782001546870072L;
	
	private long id;
	private String title;
	private String edition;
	private String isbn;
	private int nun_pages;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getNun_pages() {
		return nun_pages;
	}
	public void setNun_pages(int nun_pages) {
		this.nun_pages = nun_pages;
	}
	
	

}
