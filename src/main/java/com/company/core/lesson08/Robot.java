package com.company.core.lesson08;

class Robot implements Participant{
    private final String name;
    private final int limitRun;
    private final int limitJump;
    private boolean isFinish = false;//признак, что участник закончил гонку

    public Robot(String name, int limitRun, int limitJump) {
        this.name = name;
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public int getLimitRun() {
        return limitRun;
    }

    @Override
    public int getLimitJump() {
        return limitJump;
    }

    @Override
    public void run() {
        System.out.println(this + " пробежал дистанцию ");
    }

    @Override
    public void jump() {
        System.out.println(this + " перепрыгнул стену ");
    }

    @Override
    public String toString() {
        return "Робот " + name + "[R: " + limitRun + ", J:" + limitJump + "]";
    }
}