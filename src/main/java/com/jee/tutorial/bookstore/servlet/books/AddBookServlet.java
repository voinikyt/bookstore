package com.jee.tutorial.bookstore.servlet.books;

import com.jee.tutorial.bookstore.jpa.Book;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewBookServlet", urlPatterns = {"/books/new"})
public class AddBookServlet extends AbstractBookServlet {    
    private static final long serialVersionUID = 1L;	       
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                       
        validateBook(request);
        if (checkForValidationMessages(request)) {
            processForEditing(request, response, "/books/add.jsp");
        } else {
            createBook(request);
            response.sendRedirect(request.getContextPath() + "/books/all");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("allAuthors", authorEJB.findAll());        
        request.getRequestDispatcher("/books/add.jsp").forward(request, response);
    }

    private void createBook(HttpServletRequest request) {
        Book book = getBookFromParams(request);
        bookEJB.create(book);
    }
}
