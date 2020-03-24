package com.cs.ajinmathew.project.ebook;

public class Book {
    public String code,title,description,author,publisher,type,prize,imagepath;

    public Book(String code, String title, String description, String author, String publisher, String type, String prize, String imagepath) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.type = type;
        this.prize = prize;
        this.imagepath = imagepath;
    }

    public Book() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
