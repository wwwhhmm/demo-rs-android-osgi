package com.whm.rs_deom.to;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="books")
public class Book {
	@Element
	private String bookId;
	
	@Element
	private String bookISBNnumber;
	
	@Element
	private String bookName;
	//Let assume one author only
	
	@Element
	private String author;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookISBNnumber() {
		return bookISBNnumber;
	}
	public void setBookISBNnumber(String bookISBNnumber) {
		this.bookISBNnumber = bookISBNnumber;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}
