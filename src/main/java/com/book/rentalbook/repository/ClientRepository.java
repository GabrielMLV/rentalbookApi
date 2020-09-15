package com.book.rentalbook.repository;

import com.book.rentalbook.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//Use JpaRepository interface
@Repository
@EnableJpaRepositories
public interface ClientRepository extends JpaRepository<Client, Long> {

}
