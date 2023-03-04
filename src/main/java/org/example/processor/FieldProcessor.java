package org.example.processor;

import java.lang.reflect.Field;

public interface FieldProcessor {

    StringBuilder process(Field field) throws ClassNotFoundException;
}
