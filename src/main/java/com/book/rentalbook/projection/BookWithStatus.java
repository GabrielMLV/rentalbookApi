package com.book.rentalbook.projection;

import com.book.rentalbook.model.Book;

import javax.persistence.Column;
import java.util.Date;

public interface BookWithStatus {
    Long getId();
    String getTitle();
    String getDescription();
    Float getPrice();
    int getStatus_book();

    String getStatus();
}
