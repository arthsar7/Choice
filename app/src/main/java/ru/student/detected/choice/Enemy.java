package ru.student.detected.choice;

public class Enemy extends Human{
    private int damage;
    private Character target;

    public Enemy(String name, int health, int damage){
        super(name,health);
        this.damage = damage;
    }
    public Enemy(String name, int health){
        super(name, health);
    }
    public void attackTheCharacter(){
        if(!getTarget().isDead()){
            getTarget().changeHealth(-damage);
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }
}
