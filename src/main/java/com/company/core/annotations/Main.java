package com.company.core.annotations;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
class Cat {
    @Getter
    @Setter
    private String name;

    @Deprecated
    public void Meow() {
        System.out.println("Meow!");
    }
}

class IncFunc {

    @MyAnnotation(incNumber = 2)
    static int inc(int x) {
        //   MyAnnotation com = ;
        return x + 1;
    }
}

class Main {

    void test(@MyAnnotation int z) {

    }

    public static void main(String[] args) {
        int x = 10;
        x = IncFunc.inc(x);
        System.out.println("x= " + x);

        Cat c = new Cat();
        c.setName("Vasya");
        System.out.println(c.getName());
        c.Meow();

    }
}
