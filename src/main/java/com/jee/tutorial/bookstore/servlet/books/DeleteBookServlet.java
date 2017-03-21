/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jee.tutorial.bookstore.servlet.books;

import com.jee.tutorial.bookstore.servlet.books.AbstractBookServlet;
//import com.jee.tutorial.bookstore.ejb.BookService;
import com.jee.tutorial.bookstore.jpa.Book;
import java.io.IOException;
import java.util.logging.Logger;
//import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ikolev
 */
@WebServlet(name = "DeleteBookServlet", urlPatterns = {"/books/delete"})
public class DeleteBookServlet extends AbstractBookServlet {   
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(DeleteBookServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Book book = getBookFromParams(request);
        bookEJB.remove(getBookFromParams(request));        
        log.info(book.toString());        
        request.setAttribute("allBooks", bookEJB.findAll());        
        response.sendRedirect(request.getContextPath() + "/books/all");
    }
}
