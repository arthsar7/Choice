package ru.student.detected.choice;

import android.content.Context;
import android.media.MediaPlayer;

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
    public void check(int finalI) {
        endOfStory = true;
        if(character.isDead()){
            final MediaPlayer deathSound = MediaPlayer.create(context, R.raw.lmao);
            deathSound.start();
            story.current_situation = story.story_ending;
        }
        else if(story.current_situation == story.start_story.way[2] && (finalI == 1)){
            final MediaPlayer deadBaron = MediaPlayer.create(context, R.raw.deadbaronsound);
            deadBaron.start();
            story.current_situation = story.death_baron;
        }
        else if(story.current_situation == story.root && finalI == 0) {
            if(character.getRep() < 7) {
                story.current_situation = story.bad;
            }
            else{
                story.current_situation = story.good;
            }
        }
        else {
            go(finalI + 1);
            endOfStory = false;
        }
    }
    private void go(int i) {
        story.go(i);
        story.update();
    }

}
