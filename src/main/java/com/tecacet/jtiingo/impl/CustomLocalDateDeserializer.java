package com.tecacet.jtiingo.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.readValueAs(String.class);
        Matcher matcher = DATE_PATTERN.matcher(value);
        if (!matcher.find()) {
            return null;
        }
        String match = matcher.group();
        return LocalDate.parse(match);
    }
}
