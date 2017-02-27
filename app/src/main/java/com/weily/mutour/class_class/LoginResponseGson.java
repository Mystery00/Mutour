package com.weily.mutour.class_class;

public class LoginResponseGson
{
    private int status;
    private String mes;

    public LoginResponseGson(int status, String mes)
    {
        this.status = status;
        this.mes = mes;
    }

    public int getStatus()
    {
        return status;
    }

    public String getMes()
    {
        return mes;
    }
}
