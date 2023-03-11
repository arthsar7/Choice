package ru.student.detected.choice;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

public class StatusHandler {
    private final Hero hero;
    private Story story;
    private final Context context;
    public boolean endOfStory;
    public StatusHandler(Context context, Story story){
        this.story = story;
        this.hero = story.getHero();
        this.context = context;
        Enemy.setTarget(hero);
    }

    private void checkStats(TextView health, TextView rep, int choice){
        hero.changeRep(story.current_situation.options[choice].dR);
        setStats(health, rep);
    }

    public void setStats(TextView health, TextView rep) {
        rep.setText(String.valueOf(hero.getRep()));
        health.setText(String.valueOf(hero.getHealth()));
    }

    public void check(TextView health, TextView rep, int choice) {
        checkStats(health, rep, choice);
        endOfStory = true;
        if(story.current_situation == story.start_story.way[2]){
            Enemy angryBaron = new Enemy("Злой Барон",100,70);
            if(choice == 1) {
                angryBaron.attackTheTarget();
                hero.kill(angryBaron);
                if(angryBaron.isDead())
                    angryBaron.yell(MediaPlayer.create(context, R.raw.deadbaronsound));
                setStats(health, rep);
                story.current_situation = story.death_baron;
            }
            if(choice == 0){
                angryBaron.attackTheTarget();
                new Enemy("Стража", 100, 30).attackTheTarget();
                setStats(health, rep);
                if(hero.isDead())
                    hero.yell(MediaPlayer.create(context, R.raw.lmao));
                story.current_situation = story.story_ending;
            }
        }
        else if(story.current_situation == story.root && choice == 0) {
            story.current_situation = (hero.getRep() < 7) ? story.bad : story.good;
        }
        else {
            go(choice + 1);
            endOfStory = false;
        }
    }

    private void go(int i) {
        story.go(i);
        story.update();
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
