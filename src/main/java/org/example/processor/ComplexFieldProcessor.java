package org.example.processor;

import lombok.RequiredArgsConstructor;
import org.example.annotation.KoliberDescription;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.example.processor.FieldProcessorUtils.buildDescriptionLine;
import static org.example.processor.FieldProcessorUtils.buildDescriptionLineWithCrop;

@RequiredArgsConstructor
public class ComplexFieldProcessor implements FieldProcessor {

    private final Config config;
    private final KoliberDescription koliberDescription;

    @Override
    public StringBuilder process(Field field) throws ClassNotFoundException {
        Class<?> aClass = extractClass(field);
        StringBuilder nestedObjectDescription = Processor.of().process(aClass, config.getNextLevelConfig());
        StringBuilder buildDescriptionLine = buildDescriptionLine(config.getLineBeginning(), field.getName(), koliberDescription.comment());

        return new StringBuilder().append(buildDescriptionLine).append(nestedObjectDescription);
    }

    private static Class<?> extractClass(Field field) throws ClassNotFoundException {
        Type type = field.getGenericType();
        //for generics
        if (type instanceof ParameterizedType) {
            return extractClassForGenerics((ParameterizedType) type);
        }
        //for others
        else {
            return field.getType();
        }
    }

    private static Class<?> extractClassForGenerics(ParameterizedType type) throws ClassNotFoundException {
        Type[] actualTypeArguments = type.getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new UnsupportedOperationException("Mapping is not defined for" + type);
        }

        Type actualTypeArgument = actualTypeArguments[0];
        String typeName = actualTypeArgument.getTypeName();
        return Class.forName(typeName);
    }
}
