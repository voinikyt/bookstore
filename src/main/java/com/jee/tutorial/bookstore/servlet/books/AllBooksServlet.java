package com.jee.tutorial.bookstore.servlet.books;

import com.jee.tutorial.bookstore.ejb.BookService;
import com.jee.tutorial.bookstore.ejb.JPAService;
import com.jee.tutorial.bookstore.servlet.PaginationServlet;
import javax.ejb.EJB;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "AllBooksServlet", urlPatterns = {"/books/all", "/books/"},  initParams = {@WebInitParam(name = "DEFAULT_PAGE_SIZE", value = "2")})
public class AllBooksServlet extends PaginationServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private BookService bookService;
    
    @Override
    protected JPAService<?> getJPAService() {
        return bookService;
    }

    @Override
    protected String getCollectionName() {
        return "allBooks";
    }

    @Override
    protected String getJSPPageName() {
        return "/books/all.jsp";
    }

    @Override
    protected String getPageSizeParameterName() {
        return "allBooksPageSize";
    }	
}
