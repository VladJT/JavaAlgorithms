package com.company.core.lesson08;


class Main {

    public static void main(String[] args) {
        // участники
        Participant[] p = initParticipiants();

        // препятствия
        Obstacle[] o = initObstacles();

        startGame(p, o);// начинаем забег

        if (checkAllFinish(p)) {
            System.out.println("Победителей нет");
        } else {
            showChampions(p);
        }
    }

    private static Obstacle[] initObstacles() {
        return new Obstacle[]{
                new Wall(6),
                new Road(250),
                new Wall(9),
                new Road(260)
        };
    }

    static Participant[] initParticipiants() {
        return new Participant[]{
                new Human("Иван", 200, 10),
                new Human("Петр", 250, 9),
                new Human("Стас", 300, 11),
                new Robot("Android_1", 400, 9),
                new Robot("Android_2", 500, 8),
                new Cat("Барсик", 180, 20),
                new Cat("Мурзик", 260, 15),
        };
    }


    private static void startGame(Participant[] p, Obstacle[] o) {
        for (int i = 0; i < o.length; i++) {
            System.out.println(o[i]);

            for (int j = 0; j < p.length; j++) {
                if (p[j].isFinish()) continue;// выбывших не проверяем

                if (o[i].checkAction(p[j]) == false) {
                    System.out.println(p[j] + " не прошел препятствие и выбывает");
                    p[j].setFinish(true);
                }
            }
            System.out.println("--------");
            if (checkAllFinish(p)) break;//участники кончились - конец полосы
        }//for
    }


    private static void showChampions(Participant[] participants) {
        StringBuilder sb = new StringBuilder();
        sb.append("👑👑👑 Наши чемпионы 👑👑👑 \n");
        for (Participant p : participants) {
            if (!p.isFinish())
                sb.append(p + "\n");
        }
        System.out.println(sb);
    }


    private static boolean checkAllFinish(Participant[] participants) {
        for (Participant p : participants) {
            if (!p.isFinish())
                return false;
        }
        return true;
    }
}