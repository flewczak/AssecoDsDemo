package org.example.processor;

import org.example.annotation.KoliberDescription;
import org.example.annotation.KoliberFieldDescription;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Processor {


    /**
     * there is no guarantee about order of fields returned by java.lang.Class#getDeclaredFields(),
     * to make runs replicable I am sorting fields by java.lang.reflect.Field#getName()
     * implementation can be changed by calling org.example.processor.Processor#withCustomOrder(java.util.Comparator)
     */
    private final Comparator<Field> fieldComparator;

    private Processor(Comparator<Field> comparator) {
        fieldComparator = comparator;
    }

    private Processor() {
        fieldComparator = Comparator.comparing(Field::getName);
    }

    public static Processor of() {
        return new Processor();
    }

    public static Processor withCustomOrder(Comparator<Field> comparator) {
        return new Processor(comparator);
    }

    public String process(Class<?> clazz) throws ClassNotFoundException {
        return Processor.of().process(clazz, new Config(0, 3)).toString();
    }

    StringBuilder process(Class<?> clazz, Config config) throws ClassNotFoundException {
        checkIfClassCanBeProcessed(clazz);

        List<Field> declaredFields = fetchDeclaredFields(clazz);
        return processAllFields(config, declaredFields);
    }

    private List<Field> fetchDeclaredFields(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        return Arrays.stream(declaredFields)
                .sorted(fieldComparator)
                .collect(Collectors.toList());
    }

    private static void checkIfClassCanBeProcessed(Class<?> clazz) {
        KoliberDescription mainClassKoliberDescription = clazz.getAnnotation(KoliberDescription.class);
        if (mainClassKoliberDescription == null) {
            throw new IllegalStateException("Complex type is not annotated with KoliberDescription, not defined behavior");
        }
    }

    private static StringBuilder processAllFields(Config config, List<Field> declaredFields) throws ClassNotFoundException {
        StringBuilder result = new StringBuilder();
        for (Field field : declaredFields) {
            result.append(processField(config, field));
        }
        return result;
    }

    private static StringBuilder processField(Config config, Field field) throws ClassNotFoundException {
        FieldProcessor fieldProcessor = provideFieldProcessor(config, field);
        return fieldProcessor.process(field);
    }

    private static FieldProcessor provideFieldProcessor(Config config, Field field) {
        KoliberDescription koliberDescription = field.getAnnotation(KoliberDescription.class);
        KoliberFieldDescription koliberFieldDescription = field.getAnnotation(KoliberFieldDescription.class);

        if (koliberDescription != null && koliberFieldDescription != null) {
            throw new IllegalStateException("Cannot map field annotated with two conflicting annotations");
        }

        if (koliberDescription != null) {
            if (koliberDescription.crop()) {
                return new ComplexCroppedFieldProcessor(config, koliberDescription);
            } else {
                return new ComplexFieldProcessor(config, koliberDescription);
            }
        } else if (koliberFieldDescription != null) {
            return new SimpleFieldProcessor(config, koliberFieldDescription);
        } else {
            return new DoNothingProcessor();
        }
    }
}
