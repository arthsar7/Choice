package ru.student.detected.choice;


public class Enemy extends Human{
    private int damage;
    private static Hero target;

    public Enemy(String name, int health, int damage){
        super(name,health);
        this.damage = damage;
    }
    public Enemy(String name, int health){
        super(name, health);
    }
    public void attackTheTarget(){
        if(!getTarget().isDead()){
            getTarget().changeHealth(-damage);
        }
    }
    public void setDamage(int damage) {
        this.damage = Math.max(damage, 0);
    }

    public Hero getTarget() {
        return target;
    }

    public static void setTarget(Hero target) {
        Enemy.target = target;
    }
}
