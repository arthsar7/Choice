package ru.student.detected.choice;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

public class StatusHandler {
    private final Character character;
    private Story story;
    private final Context context;
    public boolean endOfStory;
    public StatusHandler(Context context, Character character, Story story){
        this.story = story;
        this.character = character;
        this.context = context;
    }
    public void setStory(Story story){
        this.story = story;
    }
    private void checkStats(TextView health, TextView rep, int choice){
        character.changeRep(story.current_situation.options[choice].dR);
        setStats(health, rep);
    }

    public void setStats(TextView health, TextView rep) {
        rep.setText(String.valueOf(character.getRep()));
        health.setText(String.valueOf(character.getHealth()));
    }

    public void check(TextView health, TextView rep, int choice) {
        checkStats(health,  rep, choice);
        endOfStory = true;
        if(story.current_situation == story.start_story.way[2]){
            Enemy Baron = new Enemy("Baron",100);
            Baron.setTarget(character);
            if(choice == 1) {
                enemyAttack(health, rep, Baron, 70);
                final MediaPlayer deadBaron = MediaPlayer.create(context, R.raw.deadbaronsound);
                deadBaron.start();
                story.current_situation = story.death_baron;
            }
            if(choice == 0){
                enemyAttack(health, rep, Baron, 100);
                final MediaPlayer deathSound = MediaPlayer.create(context, R.raw.lmao);
                deathSound.start();
                story.current_situation = story.story_ending;
            }
        }
        else if(story.current_situation == story.root && choice == 0) {
            story.current_situation = (character.getRep() < 7) ? story.bad : story.good;

        }
        else {
            go(choice + 1);
            endOfStory = false;
        }
    }

    private void enemyAttack(TextView health, TextView rep, Enemy Baron, int damage) {
        Baron.setDamage(damage);
        Baron.attackTheCharacter();
        setStats(health, rep);
    }

    private void go(int i) {
        story.go(i);
        story.update();
    }

}
