package org.example.java8;

import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeAPI {
    public static void main(String[] args) {
        // LocalDate - date without time
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 15);
        LocalDate nextWeek = today.plusWeeks(1);

        System.out.println("Today: " + today);
        System.out.println("Next week: " + nextWeek);

// LocalTime - time without date
        LocalTime now = LocalTime.now();
        LocalTime meetingTime = LocalTime.of(14, 30); // 2:30 PM

// LocalDateTime - date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime meeting = LocalDateTime.of(2024, 12, 25, 10, 30);

// ZonedDateTime - date time with timezone
        ZonedDateTime nowInNY = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime nowInTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

// Period - date-based amount of time
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 1);
        Period period = Period.between(start, end);
        System.out.println("Years: " + period.getYears()); // 4

// Duration - time-based amount of time
        LocalTime morning = LocalTime.of(9, 0);
        LocalTime evening = LocalTime.of(17, 0);
        Duration workDay = Duration.between(morning, evening);
        System.out.println("Work hours: " + workDay.toHours()); // 8

// Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatted = currentDateTime.format(formatter);
        System.out.println(formatted);
    }
}
