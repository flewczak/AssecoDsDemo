package org.example.dto;

import org.example.annotation.KoliberDescription;
import org.example.annotation.KoliberFieldDescription;

@KoliberDescription(comment = "Adres")
public class Address {

    @KoliberFieldDescription(comment = "Miasto")
    private String city;

    @KoliberFieldDescription(comment = "Kod pocztowy")
    private String postalCode;

    @KoliberFieldDescription(comment = "Ulica")
    private String street;

    //getter/setter

}
