package com.company.core.lesson08;


interface Run {
    void run();

    int getLimitRun();
}

interface Jump {
    void jump();

    int getLimitJump();
}


interface Participant extends Run, Jump {
    // методы для проверки участия в гонке
    void setFinish(boolean finish);

    boolean isFinish();
}