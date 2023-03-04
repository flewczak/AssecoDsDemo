package org.example.dto;

import org.example.annotation.KoliberDescription;
import org.example.annotation.KoliberFieldDescription;

import java.math.BigDecimal;

@KoliberDescription(comment = "Opis")
public class Invoice {

    @KoliberFieldDescription(comment = "Numer")
    private String number;

    @KoliberFieldDescription(comment = "Kwota")
    private BigDecimal amount;

    @KoliberDescription(comment = "Waluta")
    private Currency currency;

    @KoliberDescription(comment = "Kontrahent")
    private Contractor contractor;

    //getter/setter

}
