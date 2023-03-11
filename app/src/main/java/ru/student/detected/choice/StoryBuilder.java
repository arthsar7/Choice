package ru.student.detected.choice;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryBuilder {
    private Context context;
    private ImageView image;
    private TextView[] options_v;
    private ImageView[] cards;
    private TextView outcome;
    private Hero hero;

    public StoryBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public StoryBuilder setImage(ImageView image) {
        this.image = image;
        return this;
    }

    public StoryBuilder setOptionsView(TextView[] options_v, ImageView[] cards) {
        this.options_v = options_v;
        this.cards = cards;
        return this;
    }

    public StoryBuilder setOutcome(TextView outcome) {
        this.outcome = outcome;
        return this;
    }

        public StoryBuilder setHero(Hero hero) {
        this.hero = hero;
        return this;
    }

    public Story createStory() {
        return new Story(context, image, options_v, cards, outcome, hero);
    }
}