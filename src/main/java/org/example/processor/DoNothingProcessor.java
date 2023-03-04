package org.example.processor;

import java.lang.reflect.Field;

public class DoNothingProcessor implements FieldProcessor{
    @Override
    public StringBuilder process(Field field) throws ClassNotFoundException {
        return new StringBuilder();
    }
}
