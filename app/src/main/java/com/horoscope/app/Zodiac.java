package com.horoscope.app;

import java.io.Serializable;

/**
 * Created by osman on 12.7.2014.
 */
public class Zodiac implements Serializable{
    HoroscopeCollection.HoroscopeIdentifier identifier;
    int icon;
    int image;
    int color;
    String date;
    boolean following;

    public Zodiac(HoroscopeCollection.HoroscopeIdentifier identifier, int icon, int image, int color, String date, boolean following) {
        this.identifier = identifier;
        this.icon = icon;
        this.image = image;
        this.color = color;
        this.date = date;
        this.following = following;
    }

    public HoroscopeCollection.HoroscopeIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(HoroscopeCollection.HoroscopeIdentifier identifier) {
        this.identifier = identifier;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
