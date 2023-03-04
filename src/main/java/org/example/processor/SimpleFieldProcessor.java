package org.example.processor;

import lombok.RequiredArgsConstructor;
import org.example.annotation.KoliberFieldDescription;

import java.lang.reflect.Field;

import static org.example.processor.FieldProcessorUtils.buildDescriptionLine;

@RequiredArgsConstructor
public class SimpleFieldProcessor implements FieldProcessor {

    private final Config config;
    private final KoliberFieldDescription koliberFieldDescription;

    @Override
    public StringBuilder process(Field field) {
        return buildDescriptionLine(config.getLineBeginning(), field.getName(), koliberFieldDescription.comment());
    }
}
