package com.vonzhou.learning.springmvc.service;

import com.vonzhou.learning.springmvc.domain.Book;
import com.vonzhou.learning.springmvc.domain.Category;

import java.util.List;


public interface BookService {

    List<Category> getAllCategories();

    Category getCategory(int id);

    List<Book> getAllBooks();

    Book save(Book book);

    Book update(Book book);

    Book get(long id);

    long getNextId();

}
