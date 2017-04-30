package com.weily.mutour.class_class;

public class Discuss
{
    private String imgPath;
    private String title;
    private String hint;

    public Discuss()
    {
    }

    public Discuss(String imgPath, String title, String hint)
    {
        this.imgPath = imgPath;
        this.title = title;
        this.hint = hint;
    }

    public String getImgPath()
    {
        return imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getHint()
    {
        return hint;
    }

    public void setHint(String hint)
    {
        this.hint = hint;
    }
}
