package org.example.processor;

import lombok.RequiredArgsConstructor;
import org.example.annotation.KoliberDescription;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.example.processor.FieldProcessorUtils.buildDescriptionLine;
import static org.example.processor.FieldProcessorUtils.buildDescriptionLineWithCrop;

@RequiredArgsConstructor
public class ComplexCroppedFieldProcessor implements FieldProcessor {

    private final Config config;
    private final KoliberDescription koliberDescription;

    @Override
    public StringBuilder process(Field field) {
        return buildDescriptionLineWithCrop(config.getLineBeginning(), field.getName(), koliberDescription.comment());
    }

}
