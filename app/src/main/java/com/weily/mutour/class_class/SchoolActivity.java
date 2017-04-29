package com.weily.mutour.class_class;

public class SchoolActivity
{
    private String title;
    private String source;
    private String imgPath;
    private String imgCacheName;

    public SchoolActivity()
    {
    }

    public SchoolActivity(String title, String source, String imgPath, String imgCacheName)
    {
        this.title = title;
        this.source = source;
        this.imgPath = imgPath;
        this.imgCacheName = imgCacheName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getImgPath()
    {
        return imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    public String getImgCacheName()
    {
        return imgCacheName;
    }

    public void setImgCacheName(String imgCacheName)
    {
        this.imgCacheName = imgCacheName;
    }
}
