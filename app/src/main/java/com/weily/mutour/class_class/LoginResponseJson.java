package com.weily.mutour.class_class;

public class LoginResponseJson
{
    private int status;
    private String mes;

    public LoginResponseJson(int status, String mes)
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
