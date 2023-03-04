package org.example.dto;

import org.example.annotation.KoliberDescription;
import org.example.annotation.KoliberFieldDescription;

@KoliberDescription(comment = "Waluta")
public class Currency {

    @KoliberFieldDescription(comment = "Kod")
    private String code;



    //getter/setter

}
