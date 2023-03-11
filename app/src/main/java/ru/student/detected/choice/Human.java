package ru.student.detected.choice;

import android.media.MediaPlayer;

public class Human {
    protected int health;
    private final String name;
    public Human(String name, int health){
        this.name = name;
        this.health = health;
    }
    public void yell(MediaPlayer yell){
        yell.start();
    }
    public void rise() {
        this.health = 100;
    }

    public void changeHealth(int health) {
        this.health += health;
        if (this.isDead()) this.health = 0;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public String getName() {
        return name;
    }
}
