package com.book.rentalbook.repository;

import com.book.rentalbook.model.RentalBooks;
import com.book.rentalbook.projection.RentalBooksWeekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

//Use JpaRepository interface
@Repository
@EnableJpaRepositories
public interface RentalBooksRepository extends JpaRepository<RentalBooks, Long> {

/*    @Query(value = "SELECT rb.*, bk.title as bookTitle, cl.name as clientName FROM rental_books rb" +
                    " RIGHT JOIN book bk ON bk.id = rb.id_book" +
                    " RIGHT JOIN client cl ON cl.id = rb.id_client"+
                    " WHERE NOW() <= rb.date_end_rental AND" +
                    " (NOW() + INTERVAL '7' DAY) >= rb.date_end_rental AND" +
                    " rb.id_status_rental = ?1", nativeQuery = true)
    List<RentalWeekly> searchByDateAndStatus(int id_status_rental);*/

    @Query(nativeQuery = true, value = "SELECT rb.date_start_rental, rb.date_end_rental, rb.id_status_rental, bk.price, bk.title, st.status_name, cl.name FROM rental_books rb" +
            " INNER JOIN book bk ON bk.id = rb.id_book" +
            " INNER JOIN client cl ON cl.id = rb.id_client"+
            " INNER JOIN status_rental st ON st.id = rb.id_status_rental"+
            " WHERE NOW() <= rb.date_end_rental AND" +
            " (NOW() + INTERVAL '7' DAY) >= rb.date_end_rental AND" +
            " rb.id_status_rental = ?1" )
    List<RentalBooksWeekly> searchByDateAndStatus(int id_status_rental);

    @Query(nativeQuery = true, value = "SELECT * FROM rentabooks WHERE id_book = ?1" )
    List<RentalBooks> getRentalByIdBook(Long id);
}
