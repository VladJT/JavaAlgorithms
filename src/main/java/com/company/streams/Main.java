package com.company.streams;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        Runnable r1 = ()->{
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
        o.orElseThrow(()-> new RuntimeException("wrong"));

        Stream<String> streamA = Stream.of("a","b","c","d","e");
        streamA.forEach(System.out::print);
    }

}
