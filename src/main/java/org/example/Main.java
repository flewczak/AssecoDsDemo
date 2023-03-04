package org.example;


import org.example.dto.Invoice;
import org.example.processor.Processor;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String extracted = Processor.of().process(Invoice.class);
        System.out.println(extracted);
    }


}