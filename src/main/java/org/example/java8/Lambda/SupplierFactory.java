
package org.example.java8.Lambda;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class SupplierFactory {

    public static void main(String[] args) {
        Supplier<Double> randomValue = () -> Math.random();

        Supplier<List<String>> listFactory = ArrayList::new;

        BooleanSupplier isSunday = () -> LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY;

        System.out.println(randomValue.get());
        System.out.println(listFactory.get().stream());
    }
}