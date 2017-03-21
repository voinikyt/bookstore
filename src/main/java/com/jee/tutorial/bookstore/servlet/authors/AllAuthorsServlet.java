package com.jee.tutorial.bookstore.servlet.authors;

import com.jee.tutorial.bookstore.servlet.PaginationServlet;
import com.jee.tutorial.bookstore.ejb.AuthorService;
import com.jee.tutorial.bookstore.ejb.JPAService;
import javax.ejb.EJB;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/authors/all", "/authors/"}, initParams = {@WebInitParam(name = "DEFAULT_PAGE_SIZE", value = "2")})
public class AllAuthorsServlet extends PaginationServlet {

    @EJB
    private AuthorService authorService;
    
    @Override
    protected JPAService<?> getJPAService() {
        return authorService;
    }

    @Override
    protected String getCollectionName() {
        return "allAuthors";
    }

    @Override
    protected String getJSPPageName() {
        return "/authors/all.jsp";
    }   

    @Override
    protected String getPageSizeParameterName() {
        return "allAuthorsPageSize";
    }
}
