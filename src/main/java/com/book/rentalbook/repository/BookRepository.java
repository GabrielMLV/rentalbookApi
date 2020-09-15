package com.book.rentalbook.repository;

import com.book.rentalbook.model.Book;
import com.book.rentalbook.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Long> {
}
