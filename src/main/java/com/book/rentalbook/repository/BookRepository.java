package com.book.rentalbook.repository;

import com.book.rentalbook.model.Book;
import com.book.rentalbook.model.RentalBooks;
import com.book.rentalbook.projection.BookWithStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(nativeQuery = true, value = "SELECT bk.*, st.status_book as status FROM book bk INNER JOIN status_book st ON st.id = bk.status_book" )
    List<BookWithStatus> getAllBooks();
}
