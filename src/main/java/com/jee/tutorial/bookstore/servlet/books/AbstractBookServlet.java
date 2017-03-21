package com.jee.tutorial.bookstore.servlet.books;

import com.jee.tutorial.bookstore.ejb.AuthorService;
import com.jee.tutorial.bookstore.ejb.BookService;
import com.jee.tutorial.bookstore.jpa.Author;
import com.jee.tutorial.bookstore.jpa.Book;
import com.jee.tutorial.bookstore.jpa.validation.ValidationMessage;
import com.jee.tutorial.bookstore.jpa.validation.ValidationUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String BOOK_TITLE_PARAM = "book.title";
    public static final String BOOK_PRICE_PARAM = "book.price";
    public static final String BOOK_ISBN_PARAM = "book.isbn";
    public static final String BOOK_ID_PARAM = "book.id";
    public static final String BOOK_AUTHORS_PARAM = "book.authors";
    
    @EJB
    protected AuthorService authorEJB;
    
    @EJB
    protected BookService bookEJB;
    
    @Inject
    protected ValidationUtils validationUtils;

    protected Book getBookFromParams(HttpServletRequest request) {

        String title = request.getParameter(BOOK_TITLE_PARAM);
        String priceValue = request.getParameter(BOOK_PRICE_PARAM);
        
        Book book = new Book();        
        
        if (priceValue != null && !priceValue.isEmpty()) {
            try {
                BigDecimal price = new BigDecimal(priceValue);
                book.setPrice(price);
            } catch(NumberFormatException e) {
                validationUtils.addValidationMessage(request, new ValidationMessage("price", "Invalida price"));
            }
        }
        String isbn = request.getParameter(BOOK_ISBN_PARAM);
        String idValue = request.getParameter(BOOK_ID_PARAM);
        
        book.setId(idValue != null && !idValue.isEmpty() ? new Long(idValue) : null);
        book.setTitle(title);
        book.setIsbn(isbn);        

        String selectedAuthorIds[] = request.getParameterValues(BOOK_AUTHORS_PARAM);
        if (selectedAuthorIds != null && selectedAuthorIds.length > 0) {
            book.setAuthors(new HashSet<Author>());
            for (String authorId : selectedAuthorIds) {
                book.getAuthors().add(authorEJB.find(new Long(authorId)));
            }
        }
        return book;
    }

    protected void validateBook(HttpServletRequest request) {
        Book book = getBookFromParams(request);
        validationUtils.validateObject(request, book);
    }

    protected boolean checkForValidationMessages(HttpServletRequest request) {
        return validationUtils.areThereValidationMessages(request);
    }

    protected void processForEditing(HttpServletRequest request, HttpServletResponse response, String editingPage) throws ServletException, IOException {
        Book book = getBookFromParams(request);
        request.setAttribute("allAuthors", authorEJB.findAll());
        request.setAttribute("book", book);
        request.getRequestDispatcher(editingPage).forward(request, response);
    }

    

}
