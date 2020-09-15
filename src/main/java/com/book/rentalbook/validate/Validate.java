package com.book.rentalbook.validate;

public class Validate {

    public boolean ValidateId(long id){
        boolean resp = true;
        if (id <= 0){
            resp = false;
        }
        return resp;
    }

}
