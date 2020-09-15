package com.book.rentalbook.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@AllArgsConstructor
@NoArgsConstructor
@Data // toString, equals, hashCode, getters e setters.
@Entity(name = "rentalbooks")
public class RentalBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int id_client;
    private int id_book;

    private Date date_start_rental; //data do início do aluguel
    private Date date_end_rental; //data do fim do aluguel

    private int id_status_rental; //status do alguel ex: 1 para alugado, 2 para reservado...
    private String createdBy; //quem criou ex: admin@gmail.com

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt; //data da criação
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt; //data do update

}
