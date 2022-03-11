package com.company.core.lesson11;


import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        // Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        String[] words = {"cave", "new", "mike", "cave", "juice",
                "foo", "red", "like", "easy", "juice",
                "cave", "high", "red", "stone", "like"
        };
        System.out.println("Дан массив: " + Arrays.toString(words));

        // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Set<String> uniqueWords = new TreeSet<>(Arrays.asList(words));
        System.out.println("Уникальные слова: " + uniqueWords);

        // Посчитать, сколько раз встречается каждое слово.
        Iterator<String> i = uniqueWords.iterator();
        while (i.hasNext()) {
            String s = i.next();
            System.out.print(s + " - " + Stream.of(words).filter(n -> n.equals(s)).count());
            if (i.hasNext()) System.out.print(", ");
        }

    }
}
