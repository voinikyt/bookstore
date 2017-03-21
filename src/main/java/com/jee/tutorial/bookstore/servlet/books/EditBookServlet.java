package com.jee.tutorial.bookstore.servlet.books;

import com.jee.tutorial.bookstore.jpa.Book;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditBookServlet", urlPatterns = {"/books/edit"})
public class EditBookServlet extends AbstractBookServlet {
    private static final long serialVersionUID = 1L;            

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = bookEJB.find(getBookFromParams(request).getId());       
        request.setAttribute("book", book);
        request.setAttribute("allAuthors", authorEJB.findAll());        
        request.getRequestDispatcher("/books/editBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validateBook(request);
        if (checkForValidationMessages(request)) {
            processForEditing(request, response, "/books/editBook.jsp");
        } else {
            Book book = getBookFromParams(request);
            bookEJB.edit(book);
            response.sendRedirect(request.getContextPath() + "/books/all");
        }
    }
}
