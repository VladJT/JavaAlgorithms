package com.company.core.oop;

public class Player {
    String nickname;
    int points;
    int level;
    int money;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Player(String nickname) {
        this(nickname, 0, 0, 0);
    }

    public Player(String nickname, int points, int level, int money) {
        this.nickname = nickname;
        this.points = points;
        this.level = level;
        this.money = money;
    }

    public void show(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", points=" + points +
                ", level=" + level +
                ", money=" + money +
                '}';
    }

    public static void main(String[] args) {
        Player p1 = new Player("vlad", 2, 10, 100);
        Player p2 = new Player("stas", 1, 12, 90);
        Player p3 = new Player("newbe");

        Player[] players={p1,p2,p3};
        for(Player p : players){
            p.show();
        }
    }
}
