package com.example.warehouseapp.model.responseDto;

public class SimpleMessageDto<T> {

    public String message;
    public int code;

    public T data;

    public SimpleMessageDto(String s,int code){

        this.message=s;
        this.code=code;
    }
    public SimpleMessageDto(String s,int code,T data){

        this.message=s;
        this.code=code;
        this.data=data;
    }

}
