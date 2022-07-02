package com.company.core.oop;

class Worker {
    // TODO принято использовать такой порядок в классе: сначала все поля, потом конструктор, потом методы
    private final String name;
    private final String position;
    private final String email;
    private final String phone;
    private final double salary;
    private final int age;

    private Worker(WorkerBuilder wb) {
        this.name = wb.name;
        this.position = wb.position;
        this.email = wb.email;
        this.phone = wb.phone;
        this.salary = wb.salary;
        this.age = wb.age;
    }

    public int getAge() {
        return age;
    }

    public void show() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d c з/п %.2f [%s, %s]", position, name, age, salary, phone, email);
    }

    static class WorkerBuilder {
        private final String name;
        private final int age;

        private String position = "стажер";
        private String email = "@mail.ru";
        private String phone = "+7...";
        private double salary = 20000;


        WorkerBuilder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public WorkerBuilder position(String position) {
            this.position = position;
            return this;
        }

        public WorkerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public WorkerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public WorkerBuilder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Worker build() {
            return new Worker(this);
        }

    }


    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        workers[0] = new WorkerBuilder("Дмитрий", 58).phone("+79114556770").position("Инженер").email("dim@mail.com").build();
        workers[1] = new WorkerBuilder("Степан", 35).position("Ст.механик").email("sss@rambler.com").salary(47000).build();
        workers[2] = new WorkerBuilder("Иван", 35).position("Ст.механик").email("iva@mailbox.com").salary(95000).build();
        workers[3] = new WorkerBuilder("Андрей", 41).position("Инженер").email("andrey@rambler.com").salary(55000).build();
        workers[4] = new WorkerBuilder("Семен", 43).phone("+7908564353456").position("Ст.механик").email("semen@rambler.com").salary(34000).build();

        for (Worker w : workers) {
            if (w.getAge() > 40) w.show();
        }
    }
}