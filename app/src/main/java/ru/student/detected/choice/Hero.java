package ru.student.detected.choice;

public class Hero extends Human{
    private int rep;
    public Hero(String name, int health, int rep){
        super(name, health);
        this.rep = rep;
    }
    public void changeRep(int rep) {
        this.rep += rep;
        if (this.rep <= 0) this.rep = 0;
    }

    public void kill(Enemy enemy){
        enemy.changeHealth(-100);
    }

    public int getRep() {
        return rep;
    }

    @Override
    public void rise() {
        this.health = 100;
        this.rep = 5;
    }

}
