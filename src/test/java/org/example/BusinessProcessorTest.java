package org.example;

import org.example.dto.Invoice;
import org.example.dto.InvoiceExtended;
import org.example.processor.Processor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class BusinessProcessorTest {

    @Test
    void businessScenarioTest() throws ClassNotFoundException, IOException {
        String actual = Processor.of().process(Invoice.class);
        String expected = TestUtils.loadFile("InvoiceFile.txt");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void businessScenarioInvoiceExtendedTest() throws ClassNotFoundException, IOException {
        String actual = Processor.of().process(InvoiceExtended.class);
        String expected = TestUtils.loadFile("InvoiceExtendedFile.txt");
        Assertions.assertEquals(expected, actual);
    }


}