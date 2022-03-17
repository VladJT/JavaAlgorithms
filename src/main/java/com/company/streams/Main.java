package com.company.streams;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {

        // functional interface
        Runnable obj = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                System.out.println(this.getClass().getName());
            }
        };
        obj.run();


        //Thread
        new Thread(() -> {
            System.out.println("2");
        }).start();

        // lambda
        Runnable r1 = () -> {
            System.out.println("3");
        };
        new Thread(r1).start();


        // потоки
        List<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        l.stream().filter(p -> (p % 2 == 0)).forEach(System.out::println);

        l.stream().map(p -> "a".repeat(p)).limit(5).forEach(p -> System.out.println(p));

        System.out.println(l.stream().filter(p -> p < 6).count());

        System.out.println(l.stream().allMatch(p -> p > 1));// все элементы больше 0 ?
        System.out.println(l.stream().noneMatch(p -> p > 10));// нет элементов больше 10 ?
        System.out.println(l.stream().anyMatch(p -> p == 6));// есть хотя бы 1 элемент = 6 ?


        Optional<Integer> o = l.stream().filter(n -> n > 8).findFirst();// достаем объект из контейнера
        Integer iopt = o.orElse(-1);// если в контейнере нет значения, вернет -1
        o.ifPresent(i -> System.out.println(i + " в контейнере"));//выполняет команду если число есть в контейнере
        System.out.println(iopt);
        o.orElseThrow(() -> new RuntimeException("wrong"));

        Stream<String> streamA = Stream.of("a", "b", "c", "d", "e");
        streamA.forEach(System.out::print);
        System.out.println("\n---------");

        // Примеры использования filter, findFirst, findAny, skip, limit и count
        String t= "asdsf,cxx,wwer";
        String[] tempString =  Arrays.stream(t.split(",")).filter(n -> n.contains("xxx" + " ")).toArray(String[]::new);//поиск всех вхождений ххх
        List<String> ls = new ArrayList<>(Arrays.asList("a1", "a2", "a3", "a1", "a18","b2","b3"));
        var i = ls.stream().filter("a1"::equals).count();//Вернуть количество вхождений объекта «a1»
        String s = ls.stream().findFirst().orElse("0");//Вернуть первый элемент коллекции или 0, если коллекция пуста
        s = ls.stream().skip(ls.size() - 1).findAny().orElse("empty");//Вернуть последний элемент коллекции или «empty», если коллекция пуста
        s = ls.stream().filter(n -> n.equals("a3")).findAny().orElseThrow();//Найти элемент в коллекции равный «a3» или кинуть ошибку
        s = ls.stream().skip(2).findAny().get();//Вернуть третий элемент коллекции по порядку
        Object[] rez = ls.stream().skip(2).limit(2).toArray();//Вернуть два элемента начиная со второго
        ls.stream().filter(n -> n.contains("1")).collect(Collectors.toList());//Выбрать все элементы по шаблону - содержит 1

        // без дубликатов
       ls.stream().distinct().collect(Collectors.toList());

       //убрать первый символ и вернуть массив чисел (int[])
        int[] arr = ls.stream().map(n->n.substring(1)).mapToInt(n->Integer.parseInt(n)).toArray();
        System.out.println(Arrays.toString(arr));

        //получить сумму всех чисел, перечисленных через запятую
        int sum  = ls.stream().mapToInt(n->Integer.parseInt(n.substring(1))).sum();
        System.out.println(sum);

        //Объединить все элементы в одну строку через разделитель: и обернуть тегами <b>… </b>
        String rez2 = ls.stream().collect(Collectors.joining(":","<br>","</br>"));
        System.out.println(rez2);

        //Преобразовать в map, сгруппировав по первому символу строки
        Map<Object, List<String>> map = ls.stream().collect(Collectors.groupingBy((p->p.substring(0,1))));
        System.out.println(map);


    }

}
