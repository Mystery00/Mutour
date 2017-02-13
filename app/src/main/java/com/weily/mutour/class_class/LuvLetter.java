package com.weily.mutour.class_class;

import com.weily.mutour.R;

public class LuvLetter
{
    private String sender;
    private String text;
    private int color;

    public enum BackgroundColor
    {
        RED, PINK, DEEP_PURPLE, PURPLE, INDIGO, BLUE, LIGHT_BLUE, ORANGE, DEEP_ORANGE
    }

    public LuvLetter(String sender, String text, BackgroundColor backgroundColor)
    {
        this.sender = sender;
        this.text = text;
        switch (backgroundColor)
        {
            case RED:
                this.color = R.drawable.card_background_red;
                break;
            case PINK:
                this.color = R.drawable.card_background_pink;
                break;
            case DEEP_PURPLE:
                this.color = R.drawable.card_background_deep_purple;
                break;
            case PURPLE:
                this.color = R.drawable.card_background_purple;
                break;
            case INDIGO:
                this.color = R.drawable.card_background_indigo;
                break;
            case BLUE:
                this.color = R.drawable.card_background_blue;
                break;
            case LIGHT_BLUE:
                this.color = R.drawable.card_background_light_blue;
                break;
            case ORANGE:
                this.color = R.drawable.card_background_orange;
                break;
            case DEEP_ORANGE:
                this.color = R.drawable.card_background_deep_orange;
                break;
        }
    }

    public String getSender()
    {
        return sender;
    }

    public String getText()
    {
        return text;
    }

    public int getBackground_color()
    {
        return color;
    }
}
