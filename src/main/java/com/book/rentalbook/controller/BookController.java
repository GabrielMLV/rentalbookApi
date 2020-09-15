package com.book.rentalbook.controller;

import com.book.rentalbook.model.Book;
import com.book.rentalbook.model.RentalBooks;
import com.book.rentalbook.repository.BookRepository;
import com.book.rentalbook.repository.RentalBooksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/book"})
public class BookController {

    private ResponseEntity <?> response;

    public BookController(BookRepository repository, RentalBooksRepository repositoryRental){
        response = null;
        this.repository = repository;
        this.repositoryRental = repositoryRental;
    }

    private final BookRepository repository;
    private final RentalBooksRepository repositoryRental;

    //INSERT CLIENT
    @CrossOrigin
    @PostMapping()
    public ResponseEntity <?> create(@RequestBody Book book){
        book.setCreatedAt(new Date()); //verifica se foi passado uma data de criação
        book.setUpdatedAt(new Date()); //verifica se foi passado uma data de upadete
        Book bookResult = repository.save(book);
        ResponseEntity.ok().body(bookResult);
        return response;
    }

    // GET ALL CLIENTS
    @CrossOrigin
    @GetMapping(produces="application/json")
    public ResponseEntity <?> findAll(){
        List<Book> books = repository.findAll();
        return ResponseEntity.ok().body(books);
    }

    // GET BOOK BY ID
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
    public ResponseEntity <?> update(@PathVariable("id") long id, @RequestBody Book book) {
        if(id == 0 || book == null){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id).map(record -> {
                record.setTitle(book.getTitle());
                record.setDescription(book.getDescription());
                record.setPrice(book.getPrice());
                record.setStatus_book(book.getStatus_book());
                record.setCreatedBy(record.getCreatedBy()); //mantendo os valores de criação
                record.setCreatedAt(record.getCreatedAt()); //mantendo os valores de criação
                record.setUpdatedAt(new Date()); // nova data de update
                //save client
                Book updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
        }
        return response;
    }

    // BLOCKED BOOK
    @CrossOrigin
    @PutMapping(value="blocked/{id}")
    public ResponseEntity <?> blockedBook (@PathVariable("id") long id) {
        int status_blocked = 4; //bloqueado
        response = repository.findById(id).map(record -> {
            record.setTitle(record.getTitle());
            record.setDescription(record.getDescription());
            record.setPrice(record.getPrice());
            record.setStatus_book(status_blocked);
            record.setCreatedBy(record.getCreatedBy()); //mantendo os valores de criação
            record.setCreatedAt(record.getCreatedAt()); //mantendo os valores de criação
            record.setUpdatedAt(new Date()); // nova data de update
            //save client
            Book updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());

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
                    if(record.getStatus_book() > 1){ //Livro alugado ou reservado
                        return ResponseEntity.ok("Book in use");
                    }else{
                        List<RentalBooks> listRentalBooks = repositoryRental.getRentalByIdBook(id);
                        if(listRentalBooks.size() == 0){ //verifica se o book tem alugueis ou reservas caso não tiver é possível deletar o book
                            repository.deleteById(id);
                            return ResponseEntity.ok().build();
                        }else{ // não é possível deletar o book pois existe referências do book em outras tabelas
                            return ResponseEntity.ok("Existe um histórico para determinado livro. Impossível excluír. Tente bloquear o livro.");
                        }
                    }

                }).orElse(ResponseEntity.notFound().build());
        }
        return response;
    }
}
