package com.controlestoque;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@Component
public class StringToLocalDateTimeConverter
  implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(
          source, DateTimeFormatter.BASIC_ISO_DATE);
    }
}