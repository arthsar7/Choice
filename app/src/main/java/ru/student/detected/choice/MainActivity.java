package ru.student.detected.choice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView[] options;
    ImageView[] cards;
    MediaPlayer deathSound, deadBaron;
    TextView outcome, rep, health;
    Story story;
    int steps = 0;
    ImageView image;
    Character geralt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(MainActivity.this, SoundService.class));
        super.onCreate(savedInstanceState);
        deathSound = MediaPlayer.create(this, R.raw.lmao);
        deadBaron = MediaPlayer.create(this, R.raw.deadbaronsound);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        rep = findViewById(R.id.rep);
        cards = new ImageView[]{findViewById(R.id.card1), findViewById(R.id.card2), findViewById(R.id.card3)};
        health = findViewById(R.id.health);
        outcome = findViewById(R.id.outcome);
        options = new TextView[]{findViewById(R.id.option1), findViewById(R.id.option2), findViewById(R.id.option3)};
        geralt = new Character("Geralt", 100, 5);
        rep.setText(String.valueOf(geralt.getRep()));
        health.setText(String.valueOf(geralt.getHealth()));
        story = new Story();
        updateOptions();
        adventure();
    }

    private void adventure() {
        final MediaPlayer ch = MediaPlayer.create(this, R.raw.choice);
        for(int i = 0; i < options.length; i ++) {
            final int finalI = i;
            options[i].setOnClickListener(view -> {
                ch.start();
                geralt.changeHealth(story.current_situation.options[finalI].dH);
                geralt.changeRep(story.current_situation.options[finalI].dR);
                rep.setText(String.valueOf(geralt.getRep()));
                health.setText(String.valueOf(geralt.getHealth()));
                statusChecker(finalI);
            });
        }
    }

    private void statusChecker(int finalI) {
        if(geralt.isDead()){
            story.current_situation = story.story_ending;
            gameOver(deathSound);
        }
        else if(story.current_situation == story.start_story.way[2] && (finalI == 1)){
            story.current_situation = story.death_baron;
            gameOver(deadBaron);
        }
        else if(story.current_situation == story.root && finalI == 0) {
            if(geralt.getRep() < 7) {
                story.current_situation = story.bad;
            }
            else{
                story.current_situation = story.good;
            }
            gameOver();
        }
        else go(finalI + 1);
    }

    private void gameOver(MediaPlayer sound) {
        sound.start();
        options[1].setOnClickListener(view -> revive());
        updateOptions();
    }
    private void gameOver() {
        options[1].setOnClickListener(view -> revive());
        updateOptions();
    }

    private void revive() {
        steps = 0;
        story = new Story();
        updateOptions();
        rise();
    }

    private void go(int i) {
        steps++;
        story.go(i);
        updateOptions();
    }

    private void updateOptions() {
        for(int i = 0; i < options.length; i++){
            options[i].setText(story.current_situation.options[i].text);
            checkVisibility();
        }
        story.current_situation.setImage(this, image);
        outcome.setText(story.current_situation.text);
    }
    private void checkVisibility(){
        for(int i = 0 ; i < 3; i ++){
            cards[i].setVisibility(story.current_situation.options[i].isVisible ? View.VISIBLE : View.INVISIBLE);
            options[i].setVisibility(story.current_situation.options[i].isVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
    @Override
    protected void onDestroy() {
        stopService(new Intent(MainActivity.this, SoundService.class));
        super.onDestroy();
    }
    private void rise(){
        geralt.rise();
        rep.setText(String.valueOf(geralt.getRep()));
        health.setText(String.valueOf(geralt.getHealth()));
        adventure();
    }
}