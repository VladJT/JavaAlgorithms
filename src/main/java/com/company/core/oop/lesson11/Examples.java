package com.company.core.oop.lesson11;

import java.util.*;
import java.util.stream.Collectors;


class Examples {

    public static void main(String[] args) {
        System.out.println("----- ARRAY LIST ------");
        ArrayList<Integer> l = new ArrayList();
        l.add(1);
        l.add(2);
        l.add(211);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(2, 11);

        Iterator<?> i = l.iterator();
        while (i.hasNext()) {
            System.out.print(i.next());
            if (i.hasNext()) System.out.print("; ");
        }
        System.out.println();

        l.forEach(n -> System.out.print(n + ", "));
        System.out.println();


        Integer[] arrayInt = l.toArray(new Integer[]{});// список в массив

        Collections.shuffle(l);// перемешивание в случайном порядке
        System.out.println(l);

        Collections.sort(l, (s1, s2) -> s2.toString().length() - s1.toString().length());//сортировка по длине строки
        System.out.println(l);

        Collections.rotate(l, -2);// сдвиг влево
        System.out.println(l);

        Collections.rotate(l, 1);// сдвиг вправо
        System.out.println(l);

        List<String> ls = new ArrayList<>();//сортировка по длине строки
        ls.addAll(l.stream().map(s -> s.toString()).collect(Collectors.toList()));
        Collections.sort(ls, Comparator.comparingInt(String::length));

        Collections.sort(l);
        System.out.println(Collections.binarySearch(l, 11));//быстрый поиск индекса элемента в отсортированной коллекции

        System.out.println("----- LINKED LIST ------");
        LinkedList<Integer> lks = new LinkedList();
        lks.addAll(List.of(new Integer[]{1, 2, 3, 4, 65, 2, 5, 3, 2, 11, 0}));
        Iterator<?> ilks = lks.descendingIterator(); // обратный итератор
        while (ilks.hasNext()) System.out.print(ilks.next() + ", ");
        System.out.println(lks);


        System.out.println("----- ARRAY DEQUE like Stack------");
        ArrayDeque<String> ad = new ArrayDeque<>();
        // like Stack
        ad.push("A");
        ad.push("B");
        ad.push("C");
        System.out.println(ad);
        System.out.println(ad.pop());
        System.out.println(ad.pop());

        System.out.println("----- ARRAY DEQUE like Queue ------");
        // like Queue
        ad.clear();
        ad.add("A");
        ad.add("B");
        ad.add("C");
        System.out.println(ad);
        System.out.println(ad.pop());
        System.out.println(ad.pop());


        System.out.println("----- HASH MAP------");
        HashMap<String, String> hm = new HashMap();
        hm.put("Russia", "Moscow");
        hm.put("Spain", "Madrid");
        hm.put("England", "London");
        hm.put("Germany", "Berlin");
        hm.put("Norway", "Oslo");
//        for (Map.Entry<String, String> s : hm.entrySet()) {
//            System.out.printf("Key: %s \t %s \n", s.getKey(), s.getValue());
//        }

        hm.forEach((s, s2) -> System.out.printf("Key: %s \t %s \n", s, s2));
    }
}
