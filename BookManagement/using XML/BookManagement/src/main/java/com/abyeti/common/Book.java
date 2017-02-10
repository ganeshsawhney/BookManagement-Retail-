package com.abyeti.common;

/**
 * Model class for Book
 */
public class Book implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer bookid;
	private String isbn;
	private String bkname;
	private String authorname;
	private Integer available_units;

	public Book() {
	}

	public Book(String isbn,String bkname, String authorname,Integer available_units) {
		this.isbn=isbn;
		this.bkname = bkname;
		this.authorname = authorname;
		this.available_units=available_units;
	}

	public Integer getbookid() {
		return this.bookid;
	}
	public void setbookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getisbn() {
		return this.isbn;
	}
	public void setisbn(String isbn) {
		this.isbn = isbn;
	}

	public String getbkname() {
		return this.bkname;
	}
	public void setbkname(String bkname) {
		this.bkname = bkname;
	}

	public String getauthorname() {
		return this.authorname;
	}
	public void setauthorname(String authorname) {
		this.authorname = authorname;
	}

	public Integer getavailable_units() {
		return this.available_units;
	}
	public void setavailable_units(Integer available_units) {
		this.available_units = available_units;
	}
	public void incavailable_units() {
		this.available_units++;
	}
	public void decavailable_units() {
		this.available_units--;
	}
	public void removebook() {
		this.available_units=0;
	}

	public void setall4values(String isbn,String bkname,String authorname,Integer available_units)
	{
		this.isbn=isbn;
		this.bkname=bkname;
		this.authorname=authorname;
		this.available_units=available_units;
	}
}
