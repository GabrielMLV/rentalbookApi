package com.book.rentalbook.object;

import com.book.rentalbook.model.Book;
import com.book.rentalbook.model.Client;
import com.book.rentalbook.model.Rental_books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalWeekly {

 private Rental_books rental_books;
 private String bookTitle;
 private String clientName;
}
