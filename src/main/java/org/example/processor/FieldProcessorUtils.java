package org.example.processor;

public class FieldProcessorUtils {

    public static final String LINE_SEPARATOR = "\r\n";

    static StringBuilder buildDescriptionLine(String spaces, String fieldName, String comment) {
        return buildBaseDescriptionLine(spaces, fieldName, comment)
                .append(LINE_SEPARATOR);
    }

    static StringBuilder buildDescriptionLineWithCrop(String spaces, String fieldName, String comment) {
        return buildBaseDescriptionLine(spaces, fieldName, comment)
                .append("(...)")
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder buildBaseDescriptionLine(String spaces, String fieldName, String comment) {
        return new StringBuilder()
                .append(spaces)
                .append(fieldName)
                .append(" - ")
                .append(comment);
    }

}
