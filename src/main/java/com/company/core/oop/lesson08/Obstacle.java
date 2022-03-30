package com.company.core.oop.lesson08;

interface Obstacle {
    boolean checkAction(Participant participant);
}

class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean checkAction(Participant participant) {
        if (participant.getLimitJump() >= height) {
            participant.jump();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Препятствие: стена высотой " + height;
    }

}

class Road implements Obstacle {
    private int length;

    public Road(int length) {
        this.length = length;
    }

    @Override
    public boolean checkAction(Participant participant) {
        if (participant.getLimitRun() >= length) {
            participant.run();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Препятствие: дорожка длиной " + length;
    }
}