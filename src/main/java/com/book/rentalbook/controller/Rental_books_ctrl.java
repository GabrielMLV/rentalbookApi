package com.book.rentalbook.controller;

import com.book.rentalbook.model.Book;
import com.book.rentalbook.model.Rental_books;
import com.book.rentalbook.object.RentalWeekly;
import com.book.rentalbook.projection.RentalBooksWeekly;
import com.book.rentalbook.repository.BookRepository;
import com.book.rentalbook.repository.RentalBooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/rentalBook"})
public class Rental_books_ctrl {
    private ResponseEntity<?> response ;
    //CONSTRUCTOR
    public Rental_books_ctrl(RentalBooksRepository repository, BookRepository repositoryBook){
        response = null;
        this.repository = repository;
        this.repositoryBook = repositoryBook;
    }
    private final RentalBooksRepository repository;
    private final BookRepository repositoryBook;

    //INSERT RENTAL BOOK
    @CrossOrigin
    @PostMapping(path = {"/inserRental"})
    public ResponseEntity <?> createRentalBook(@RequestBody Rental_books rentalBooks){
        if(rentalBooks == null){
            response = ResponseEntity.badRequest().build();
        }else {
            int book_rental = 1; //status alugado in rental_books
            Long id_book = (long) rentalBooks.getId_book(); //convert to long
            Book book = repositoryBook.findById(id_book).map(record -> record) //get book
                    .orElse(null);
            if(book == null){
                response = ResponseEntity.notFound().build();
            }else{
                book.setStatus_book(2); // change status book Alugado
                repositoryBook.save(book);
                rentalBooks.setId_status_rental(book_rental);
                rentalBooks.setCreatedAt(rentalBooks.getCreatedAt() == null ? new Date() : rentalBooks.getCreatedAt());
                rentalBooks.setUpdatedAt(rentalBooks.getUpdatedAt() == null ? new Date() : rentalBooks.getUpdatedAt());
                Rental_books resultRb = repository.save(rentalBooks);
                response = ResponseEntity.ok().body(resultRb);
            }

        }
        return response;
    }

    //INSERT RESERVED BOOK
    @CrossOrigin
    @PostMapping(path = {"/inserReserved"})
    public ResponseEntity <?> createReservedBook(@RequestBody Rental_books rentalBooks){
        if(rentalBooks == null){
            response = ResponseEntity.badRequest().build();
        }else {
            int book_reserved = 2; //status reserved in rental_books
            Long id_book = (long) rentalBooks.getId_book(); //convert to long
            Book book = repositoryBook.findById(id_book).map(record -> record) //get book
                    .orElse(null);
            if(book == null){
                response = ResponseEntity.notFound().build();
            }else{
                book.setStatus_book(3); // change status book Reservado
                repositoryBook.save(book);
                rentalBooks.setId_status_rental(book_reserved);
                rentalBooks.setCreatedAt(rentalBooks.getCreatedAt() == null ? new Date() : rentalBooks.getCreatedAt());
                rentalBooks.setUpdatedAt(rentalBooks.getUpdatedAt() == null ? new Date() : rentalBooks.getUpdatedAt());
                Rental_books resultRb = repository.save(rentalBooks);
                response = ResponseEntity.ok().body(resultRb);
            }

        }
        return response;
    }

    // GET ALL RENTAL BOOKS
    @CrossOrigin
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <?> findAll(){
        List<Rental_books> rentalBooks = repository.findAll();
        return ResponseEntity.ok().body(rentalBooks);
    }

    // GET RENTAL BOOK BY ID
    @CrossOrigin
    @GetMapping(path = {"/{id}"})
    public ResponseEntity <?> findById(@PathVariable long id){
        if(id == 0){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    // UPDATE
    @CrossOrigin
    @PutMapping(value="/{id}")
    public Object update(@PathVariable("id") long id, @RequestBody Rental_books rentalBooks) {
        if(id == 0){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id).map(record -> {
                record.setId_client(rentalBooks.getId_client());
                record.setId_book(rentalBooks.getId_book());
                record.setId_status_rental(rentalBooks.getId_status_rental());
                record.setDate_end_rental(rentalBooks.getDate_start_rental());
                record.setDate_end_rental(rentalBooks.getDate_end_rental());

                record.setCreatedBy(record.getCreatedBy()); //mantendo os valores de criação
                record.setCreatedAt(record.getCreatedAt()); //mantendo os valores de criação
                record.setUpdatedAt(rentalBooks.getUpdatedAt() == null ? new Date() : rentalBooks.getUpdatedAt()); //verifica se foi passado uma nova data de update
                //save client
                Rental_books updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    // CANCEL RESERVED
    @CrossOrigin
    @PutMapping(value="/cancelReserved/{id}")
    public Object cancelRental (@PathVariable("id") long id) {
        if(id == 0){
            response = ResponseEntity.badRequest().build();
        }else{
            Rental_books item = repository.findById(id).map(record -> record) //pegar o rental_book
                    .orElse(null);
            if(item == null){
                response =  ResponseEntity.notFound().build();
            }else{
                int status_rental = item.getId_status_rental(); //status rental
                long id_book = item.getId_book();
                Book book = repositoryBook.findById(id_book).map(record -> record) //pegar o book
                        .orElse(null);
                if(status_rental == 1){ //status alugado
                    response = ResponseEntity.badRequest().build(); //não aceita cancelar livro com status em "alugado"
                }else if(status_rental == 2){ //status reservado
                    book.setStatus_book(1); //disponível
                    repositoryBook.save(book);

                    item.setId_status_rental(3); //status cancelado
                    item.setUpdatedAt(item.getUpdatedAt() == null ? new Date() : item.getUpdatedAt());
                    Rental_books updated = repository.save(item);
                    response = ResponseEntity.ok().body(updated);
                }
            }
        }
        return response;
    }

    // DEVOLUTION BOOK
    @CrossOrigin
    @PutMapping(value="/devolutionBook/{id}")
    public Object devolutionBook (@PathVariable("id") long id) {
        if(id == 0){
            response = ResponseEntity.badRequest().build();
        }else{
            Rental_books item = repository.findById(id).map(record -> record) //pegar o rental_book
                    .orElse(null);
            if(item == null){
                response =  ResponseEntity.notFound().build();
            }else{
                int status_rental = item.getId_status_rental(); //status rental
                long id_book = item.getId_book();
                Book book = repositoryBook.findById(id_book).map(record -> record) //pegar o book
                        .orElse(null);
                if(status_rental == 2){ //status reservado
                    response = ResponseEntity.badRequest().build(); //não aceita cancelar livro com status em "Reservado"
                }else if(status_rental == 1){ //status alugado
                    book.setStatus_book(1); //disponível
                    repositoryBook.save(book);

                    item.setId_status_rental(4); //status devolvido
                    item.setUpdatedAt(item.getUpdatedAt() == null ? new Date() : item.getUpdatedAt());
                    Rental_books updated = repository.save(item);
                    response = ResponseEntity.ok().body(updated);
                }
            }
        }
        return response;
    }


    // DELETE
    @CrossOrigin
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        if(id == 0){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    // GET ALL BY ONE WEEK AND STATUS RENTAL
    @CrossOrigin
    @GetMapping(path ={"/getRentalBooks"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <?> findAllRentalByDate(){
        int id_status_rental = 1; // livros alugados
        List<RentalBooksWeekly> rentalBooks = repository.searchByDateAndStatus(id_status_rental);
        return ResponseEntity.ok().body(rentalBooks);
    }

    // GET ALL BY ONE WEEK AND STATUS RESERVE
    @CrossOrigin
    @GetMapping(path ={"/getReservedBooks"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <?> findAllReservedByDate(){
        int id_status_rental = 2; // livros reservados
        List<RentalBooksWeekly> rentalBooks = repository.searchByDateAndStatus(id_status_rental);
        return ResponseEntity.ok().body(rentalBooks);
    }


}
