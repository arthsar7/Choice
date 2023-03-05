package ru.student.detected.choice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView[] options;
    private ImageView[] cards;
    private TextView outcome, rep, health;
    private Story story;
    private ImageView image;
    private Character geralt;
    private StatusHandler statusHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(MainActivity.this, SoundService.class));
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        rep = findViewById(R.id.rep);
        image = findViewById(R.id.image);
        cards = new ImageView[]{findViewById(R.id.card1), findViewById(R.id.card2), findViewById(R.id.card3)};
        health = findViewById(R.id.health);
        outcome = findViewById(R.id.outcome);
        options = new TextView[]{findViewById(R.id.option1), findViewById(R.id.option2), findViewById(R.id.option3)};
        story = new Story(this, image, options, cards, outcome);
        story.update();
        geralt = new Character("Geralt", 100, 5);
        statusHandler = new StatusHandler(this, geralt, story);
        statusHandler.setStats(health, rep);
        adventure();
    }

    private void adventure() {
        statusHandler = new StatusHandler(this, geralt, story);
        final MediaPlayer ch = MediaPlayer.create(this, R.raw.choice);
        for(int i = 0; i < options.length; i ++) {
            final int choice = i;
            options[i].setOnClickListener(view -> {
                ch.start();
                statusHandler.check(health, rep, choice);
                if(statusHandler.endOfStory) gameOver();
            });
        }
    }

    private void gameOver() {
        options[1].setOnClickListener(view -> revive());
        story.update();
    }

    private void revive() {
        story = new Story(this, image, options, cards, outcome);
        story.update();
        rise();
    }
    @Override
    protected void onResume(){
        startService(new Intent(MainActivity.this, SoundService.class));
        super.onResume();
    }
    @Override
    protected void onStop() {
        stopService(new Intent(MainActivity.this, SoundService.class));
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        stopService(new Intent(MainActivity.this, SoundService.class));
        super.onDestroy();
    }
    private void rise(){
        geralt.rise();
        statusHandler.setStats(health, rep);
        adventure();
    }
}