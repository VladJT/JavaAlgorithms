package com.company.core.threads;


class Pen {
    private Partner owner;
    public Pen(Partner d) {
        owner = d;
    }
    public Partner getOwner() {
        return owner;
    }
    public synchronized void setOwner(Partner d) {
        owner = d;
    }
    public synchronized void use() {
        System.out.printf("%s is signing!", owner.getName());
    }
}

class Partner {
    private String name;
    private boolean isSigned;

    public Partner(String n) {
        name = n;
        isSigned = false;
    }

    public String getName() {
        return name;
    }

    public boolean wasNotSign() {
        return !isSigned;
    }

    public void signContract(Pen pen, Partner contractor) {
        while (wasNotSign()) {
            // Если нет ручки, то ждем пока партнер нам ее отдаст.
            if (pen.getOwner() != this) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    continue;
                }
                continue;
            }else {
                // Если партнер не подписал контракт, передать ему ручку.
                if (contractor.wasNotSign()) {
                    System.out.printf(
                            "%s: You sign first, %s!%n",
                            name, contractor.getName());
                    pen.setOwner(contractor);
                    continue;
                }
                // Подписать контракт
                pen.use();
                isSigned = true;
                System.out.printf(
                        "%s: I signed!%n",
                        name);
                // Передать ручку
                pen.setOwner(contractor);
            }
        }
    }
}

public class LiveLock {
    public static void main(String[] args) {
        final Partner cat = new Partner("Basilio");
        final Partner fox = new Partner("Alice");
        final Pen s = new Pen(cat);
        new Thread(() -> cat.signContract(s, fox)).start();
        new Thread(() -> fox.signContract(s, cat)).start();
    }
}
