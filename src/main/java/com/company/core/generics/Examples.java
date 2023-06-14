package com.company.core.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Examples {
}

// И ошибается, пытается прочитать из этого списка целое значение. При этом приложение прекрасно
//скомпилируется, но упадет в рантайме. Если использовать дженерик тип списка, то мы заранее
//предусматриваем, что в списке могут хранится только строковые значения, и в этом случае, упадет
//компиляция.
class GenericFailed {
    public static void main(String[] argv) {
        List persons = new ArrayList();
        persons.add("John");
        persons.add("Jimmy");
        // Здесь ошибка
        Integer num = (Integer) persons.get(0);
        System.out.println(num);
    }
}

//
class GenericGood {
    public static void main(String[] argv) {
        List<String> persons = new ArrayList<>();
        persons.add("John");
        persons.add("Jimmy");
        showList(persons);
        List<Integer> ages = new ArrayList<>();
        ages.add(25);
        ages.add(20);
        showList(ages);
    }

    // Маска типа, можем подставить все что угодно вместо вопроса
    private static void showList(List<?> objects) {
        for (Object obj : objects) {
            System.out.println(obj);
        }
    }
}


//Задание метода с параметрическим типом. Чтобы задать метод, который принимает на вход или
//возвращает параметрический тип, то это нужно указать при описании метода следующим образом
//<T> T method(T param).
class ParamMethods {
    public static void main(String[] argv) {
        List<String> names = new ArrayList<>();
        names.add("Bob");
        names.add("Uncle");
        swap(names, 0, 1);
        System.out.println(names);
    }

    private static void swap(List<?> list, int index1, int index2) {
        swapCore(list, index1, index2);
    }

    private static <T> void swapCore(List<T> list, int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}


//Но параметризованные типы можно сделать ковариантными, можно при помощи маски указать, что
//коллекции приемнику с базовым параметризованным типом можно присваивать коллекцию с
//конкретным типом. Таким образом осуществляется наследование с сохранением иерархии в
//параметризованных типах.
class ExtRule {
    public static void main(String[] argv) {
        List<Double> cash = new ArrayList<>();
        cash.add(5.3);
        showWholePart(cash);
        List<Integer> ages = new ArrayList<>();
        ages.add(5);
        showWholePart(ages);

    }

    // Маска типа, можем подставить числовые типы вместо вопроса
    private static void showWholePart(List<? extends Number> numbers) {
        for (Number num : numbers) {
            System.out.format("Number = %f%n", num.doubleValue());
        }
    }
}

//Контравариантность - это обращение ковариантности, то есть указание, что иерархия исходных типов
//будет обращаться на противоположную в производных типах.
class Contr {
    public static void main(String[] argv) {
        List<Number> numbers = new ArrayList<Number>();
        List<? super Integer> integers = numbers;
    }
}

/*
 * Необходимо помнить, что, если коллекция объявлена с маской <? extends Type>, то значения из нее
 * можно только читать. В список ничего нельзя добавить за исключением null. Если же коллекция
 * объявлена как <? super Type>, то в нее можно только добавлять, а читать можно только Object.
 * PECS расшифровывается как Producer Extends Consumer Super. То есть из одного
 * параметризованного типа можно только читать, с ограничением сверху (extends), а в другой только
 * писать, с ограничением снизу (super)
 */
class PECS {
    public static void main(String[] argv) {
        List<Number> numbers = new ArrayList<>();
        List<Integer> integers = Arrays.asList(1, 2);
        copy(integers, numbers);
        System.out.println(numbers);
    }

    private static <T> void copy(List<? extends T> source, List<? super T>
            destination) {
        for (int i = 0; i < source.size(); i++) {
            T element = source.get(i);
            destination.add(element);
        }
    }
}
