package com.brandwatch.ivanatwitterapp.api.utils;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

@Deprecated
public class DateParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = new DateTimeFormatterBuilder()
            .appendPattern("yyyy[-M[-d['T'HH:mm[:ss[.")
            .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, false)
            .appendPattern("]][ZZZ][ZZZZZ]]]]")
            .parseDefaulting(MONTH_OF_YEAR, 1)
            .parseDefaulting(DAY_OF_MONTH, 1)
            .toFormatter(Locale.ROOT);

    /**
     * @param input String that represents date in multiple date formats:
     *              2018-08-07T10:46:30+00:00<br>
     *              2018-08-07T10:46:30Z<br>
     *              2018-08-07T10:46<br>
     *              2018-08-07
     * @return LocalDateTime in UTC timezone
     */
    public static LocalDateTime parseDateTime(final String input) {
        final TemporalAccessor parsed
                = DATE_TIME_FORMATTER.parseBest(
                input,
                ZonedDateTime::from,
                LocalDateTime::from,
                LocalDate::from
        );

        if (parsed instanceof ZonedDateTime) {
            return ((ZonedDateTime) parsed).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        } else if (parsed instanceof LocalDateTime) {
            return ((LocalDateTime) parsed);
        } else if (parsed instanceof LocalDate) {
            return ((LocalDate) parsed).atStartOfDay();
        }

        throw new IllegalStateException("Unexpected date value: " + parsed);
    }
}
