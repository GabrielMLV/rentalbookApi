package com.book.rentalbook.controller;


import com.book.rentalbook.model.Client;
import com.book.rentalbook.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/client"})
public class ClientController {

    private ResponseEntity <?> response ;
    //CONSTRUCTOR
    public ClientController(ClientRepository repository){
        response = null;
        this.repository = repository;
    }
    private final ClientRepository repository;

    //INSERT CLIENT
    @CrossOrigin
    @PostMapping()
    public ResponseEntity <?> create(@RequestBody Client client){
        client.setCreatedAt(new Date());
        client.setUpdatedAt(new Date());
        Client clientResult = repository.save(client);
        return ResponseEntity.ok().body(clientResult);
    }

    // GET ALL CLIENTS
    @CrossOrigin
    @GetMapping()
    public ResponseEntity <?> findAll(){
        List<Client> clients = repository.findAll();
        return ResponseEntity.ok().body(clients);
    }

    // GET CLIENT BY ID
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
    public Object update(@PathVariable("id") long id, @RequestBody Client client) {
        if(id == 0){
            response = ResponseEntity.badRequest().build();
        }else{
            response = repository.findById(id).map(record -> {
                record.setName(client.getName());
                record.setEmail(client.getEmail());
                record.setPhone(client.getPhone());
                record.setCreatedBy(record.getCreatedBy()); //mantendo os valores de criação
                record.setCreatedAt(record.getCreatedAt()); //mantendo os valores de criação
                record.setUpdatedAt(new Date()); //verifica se foi passado uma nova data de update
                //save client
                Client updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
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




}
