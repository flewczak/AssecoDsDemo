package org.example;

import org.example.annotation.KoliberDescription;
import org.example.annotation.KoliberFieldDescription;
import org.example.processor.Processor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProcessorTest {

    @Test
    void notProcessedIfMissingKoliberDescriptionAnnotation() {
        Assertions.assertThrows(IllegalStateException.class, () -> Processor.of().process(MissingKoliberDescriptionAnnotation.class));
    }

    @Test
    void notProcessedIfMissingKoliberDescriptionAnnotationNested() {
        Assertions.assertThrows(IllegalStateException.class, () -> Processor.of().process(MissingKoliberDescriptionAnnotationNested.class));
    }

    @Test
    void classHasOnlyKoliberFieldDescriptionAnnotations() throws ClassNotFoundException, IOException {
        doTests(SimpleClass.class, "SimpleClass.txt");
    }

    @Test
    void classHasInnerComplexType() throws ClassNotFoundException, IOException {
        doTests(ComplexClass.class, "ComplexClass.txt");
    }

    @Test
    void classHasMultipleNextedLevels() throws ClassNotFoundException, IOException {
        doTests(NestedComplexClass.class, "NestedComplexClass.txt");
    }

    @Test
    void classHasGenericCollection() throws ClassNotFoundException, IOException {
        doTests(GenericClass.class, "GenericClass.txt");
    }

    @Test
    void classHasGenericMap() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> Processor.of().process(GenericMapClass.class));
    }

    private static void doTests(Class<?> clazz, String fileName) throws ClassNotFoundException, IOException {
        String actual = Processor.of().process(clazz);
        String expected = TestUtils.loadFile(fileName);
        Assertions.assertEquals(expected, actual);
    }

    @KoliberDescription(comment = "ComplexClass")
    static class GenericClass {
        @KoliberDescription(comment = "SimpleClassList")
        List<SimpleClass> simpleClasses;
    }

    @KoliberDescription(comment = "ComplexClass")
    static class GenericMapClass {
        @KoliberDescription(comment = "SimpleClassMap")
        Map<String,SimpleClass> simpleClasses;
    }


    @KoliberDescription(comment = "ComplexClass")
    static class NestedComplexClass {
        @KoliberFieldDescription(comment = "fieldValue5")
        private String field5;

        @KoliberDescription(comment = "ComplexClass")
        private ComplexClass complexClass;

        @KoliberFieldDescription(comment = "fieldValue6")
        private String field6;

        @KoliberDescription(comment = "SimpleClass")
        private SimpleClass simpleClass;
    }

    @KoliberDescription(comment = "ComplexClass")
    static class ComplexClass {
        @KoliberFieldDescription(comment = "fieldValue3")
        private String field3;

        @KoliberDescription(comment = "SimpleClass")
        private SimpleClass simpleClass;

        @KoliberFieldDescription(comment = "fieldValue4")
        private String field4;
    }

    @KoliberDescription(comment = "SimpleClass")
    static class SimpleClass {
        @KoliberFieldDescription(comment = "fieldValue1")
        private String field1;
        @KoliberFieldDescription(comment = "fieldValue2")
        private String field2;
    }

    static class MissingKoliberDescriptionAnnotation {
        @KoliberFieldDescription(comment = "field")
        private String field;
    }

    @KoliberDescription(comment = "nested")
    static class MissingKoliberDescriptionAnnotationNested {
        @KoliberDescription(comment = "field")
        private MissingKoliberDescriptionAnnotation field;
    }
}
