package org.example.dto;

import org.example.annotation.KoliberDescription;
import org.example.annotation.KoliberFieldDescription;

import java.util.List;

@KoliberDescription(comment = "Kontrahent")
public class Contractor {

    @KoliberFieldDescription(comment = "Nazwa")
    private String name;

    @KoliberFieldDescription(comment = "NIP")
    private String nip;

    @KoliberDescription(comment = "Adresy")
    private List<Address> addresses;

    //getter/setter

}
