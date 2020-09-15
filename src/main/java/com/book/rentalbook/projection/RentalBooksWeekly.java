package com.book.rentalbook.projection;

import java.util.Date;


public interface RentalBooksWeekly {

    //RentalBooks
    Date getDate_start_rental();
    Date getDate_end_rental();
    int getId_status_rental();
    //Book
    String getTitle();
    Float getPrice();
    //Client
    String getName();
    //status_rental
    String getStatus_name();



}
