package com.brandwatch.ivanatwitterapp.api.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class DateParserTest {

    @Test
    public void convertToLocalDate() {
        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JANUARY, 29, 0, 0);
        Assert.assertEquals(localDateTime, DateParser.parseDateTime("2017-01-29"));
    }

    @Test
    public void convertToLocalDateWithTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JANUARY, 29, 18, 55);
        Assert.assertEquals(localDateTime, DateParser.parseDateTime("2017-01-29T18:55"));
    }

    @Test
    public void convertToLocalDateWithTimeAndZone() {
        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JANUARY, 29, 18, 55);
        ZoneId zoneId = ZoneId.of("UTC+0");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        Assert.assertEquals(zonedDateTime.toLocalDateTime(), DateParser.parseDateTime("2017-01-29T18:55Z"));
    }

    @Test(expected = DateTimeParseException.class)
    public void whenDateStringIsInvalid_thenThrowException() {
        DateParser.parseDateTime("Some random string");
    }
}