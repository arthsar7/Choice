package ru.student.detected.choice;

public class Character {
    private int health, rep;
    private String name;
    public Character(String name, int health, int rep){
        this.name = name;
        this.health = health;
        this.rep = rep;
    }
    public void rise() {

        this.health = 100;
        this.rep = 5;
    }

    public void changeHealth(int health) {
        this.health += health;
        if (this.health <= 0) this.health = 0;
    }

    public int getRep() {
        return rep;
    }
    public int getHealth() {
        return health;
    }

    public void changeRep(int rep) {

        this.rep += rep;
        if (this.rep <= 0) this.rep = 0;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isDead(){
        return health <= 0;
    }
}
