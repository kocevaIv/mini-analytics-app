package com.brandwatch.ivanatwitterapp.api.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Helper utility class for converting and validating dates
 */
public class DateUtils {

    public static boolean validateRequestedDateRange(Instant startDate, Instant endDate) {
        return startDate.isBefore(endDate);
    }

    public static Instant parseDate(LocalDateTime date) {
        return date.toInstant(ZoneOffset.UTC);
    }
}
