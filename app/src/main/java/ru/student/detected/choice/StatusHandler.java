package ru.student.detected.choice;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

public class StatusHandler {
    private final Character character;
    private final Story story;
    private final Context context;
    public boolean endOfStory;
    public StatusHandler(Context context, Character character, Story story){
        this.story = story;
        this.character = character;
        this.context = context;
    }
    private void checkStats(TextView health, TextView rep, int choice){
        character.changeHealth(story.current_situation.options[choice].dH);
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
        if(character.isDead()){
            final MediaPlayer deathSound = MediaPlayer.create(context, R.raw.lmao);
            deathSound.start();
            story.current_situation = story.story_ending;
        }
        else if(story.current_situation == story.start_story.way[2] && choice == 1){
            final MediaPlayer deadBaron = MediaPlayer.create(context, R.raw.deadbaronsound);
            deadBaron.start();
            story.current_situation = story.death_baron;
        }
        else if(story.current_situation == story.root && choice == 0) {
            story.current_situation = (character.getRep() < 7) ? story.bad : story.good;

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

}
