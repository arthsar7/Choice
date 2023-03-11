package ru.student.detected.choice;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;


public class Situation {
    public Situation[] way;
    public String label;
    public String text;
    public int imageID;
    public Option[] options;

    static class Option{
        public String text;
        public int dR;
        public boolean isVisible;
        Option(String text, int dR, boolean isVisible){
            this.text = text;
            this.dR = dR;
            this.isVisible = isVisible;
        }
    }
    public Situation(String label, String text, int imageID, Option[] options){
        this.label = label;
        this.text = text;
        this.imageID = imageID;
        way = new Situation[3];
        this.options = options;
    }

    public void setImage(Context context, ImageView imageView) {
        imageView.setImageDrawable(ContextCompat.getDrawable(context, imageID));
    }
}
