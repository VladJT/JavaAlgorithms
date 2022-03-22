package com.company.core.threads;

// поток через функциональный интерфейс
class MyRunableClass implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<1000;i++)
        System.out.print(i+" ["+this.hashCode()+"], ");
    }
}

// поток через наследование от Thread
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++)
            System.out.print(i+" ["+this.getName()+"], ");
    }
}

//В большинстве случаев создавать подкласс, порожденный от
//класса Thread, следует в случае, если требуется дополнить его новыми функциями. Так, если
//переопределять любые другие методы из класса Thread не нужно, то можно ограничиться только
//реализацией интерфейса Runnable. Кроме того, реализация интерфейса Runnable позволяет
//создаваемому потоку наследовать класс, отличающийся от Thread.


class Examples {
    public static void main(String[] args) {
//        new Thread(new MyRunableClass()).start();
//        new Thread(new MyRunableClass()).start();
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.setPriority(10);
        t2.setPriority(1);
        t1.start();
        t2.start();
    }


}
