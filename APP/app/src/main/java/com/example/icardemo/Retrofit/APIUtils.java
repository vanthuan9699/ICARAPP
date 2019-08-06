package com.example.icardemo.Retrofit;

public class APIUtils {
    public static final String Base_Url ="http://127.0.0.1:8080/icarserver/";

    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
