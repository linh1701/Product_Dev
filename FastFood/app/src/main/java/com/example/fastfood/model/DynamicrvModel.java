package com.example.fastfood.model;

public class DynamicrvModel {
    String name;
    private int image;

    public DynamicrvModel(String name, int image) {
        this.name = name;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public int getImage(){
        return image;
    }
}
