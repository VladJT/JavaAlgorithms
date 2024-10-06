package com.company;

class Friend {
    private final String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void bow(Friend bower) {
        try {
            Thread.sleep(1000);
            System.out.format("%s: %s has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void bowBack(Friend bower) {
        try {
            Thread.sleep(1000);
            System.out.format("%s: %s has bowed back to me!%n",
                    this.name, bower.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Main {
    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
        System.out.println("end bows");
    }
}
