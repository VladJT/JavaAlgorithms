package com.company.core.oop.lesson11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        // Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        String[] words = {"cave", "cave", "high", "cave", "juice",
                "foo", "red", "stone", "easy", "juice",
                "cave", "stone", "red", "stone", "like"
        };
        System.out.println("Дан массив: " + Arrays.toString(words));

        // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        System.out.println("Уникальные слова: " + Arrays.stream(words).distinct().toList());


        // Посчитать, сколько раз встречается каждое слово.
        Comparator<Object> mySortOrder = Comparator.comparingInt(s -> (Integer.parseInt(s.toString().split(": ")[1]))).reversed();

        System.out.println("Подсчет повторов слов (по убыванию): " +
                Arrays.stream(words)
                        .distinct()
                        .map(n -> n + ": " + Stream.of(words).filter(s -> s.equals(n)).count())
                        .sorted(mySortOrder)
                        .collect(Collectors.joining("; ")));
    }
}
