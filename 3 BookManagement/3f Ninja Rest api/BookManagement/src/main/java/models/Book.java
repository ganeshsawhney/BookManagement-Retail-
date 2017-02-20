package models;

import javax.persistence.*;

import java.util.Scanner;

@Entity
@Table(name = "book", catalog = "bookmanagementdb")
public class Book implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "isbn")
	private long isbn;

	@Column(name = "name")
	private String name;

	@Column(name = "authorId")
	private Integer authorId;

	@Column(name = "edition")
	private String edition;

	@Column(name = "price")
	private Integer price;

	@Column(name = "availableUnits")
	private Integer availableUnits;

	public Book() {
	}

	public Book(long isbn, String name, Integer authorId, String edition, Integer price, Integer availableUnits) {
		this.isbn = isbn;
		this.name = name;
		this.authorId = authorId;
		this.edition = edition;
		this.price = price;
		this.availableUnits = availableUnits;
	}

	public long getisbn() {
		return this.isbn;
	}

	@GeneratedValue
	public void setisbn(long isbn) {
		this.isbn = isbn;
	}

	public String getname() {
		return this.name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public Integer getauthorId() {
		return this.authorId;
	}

	public void setauthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getedition() {
		return edition;
	}

	public void setedition(String edition) {
		this.edition = edition;
	}

	public Integer getprice() {
		return price;
	}

	public void setprice(Integer price) {
		this.price = price;
	}

	public Integer getavailableUnits() {
		return this.availableUnits;
	}

	public void setavailableUnits(Integer availableUnits) {
		this.availableUnits = availableUnits;
	}
}
