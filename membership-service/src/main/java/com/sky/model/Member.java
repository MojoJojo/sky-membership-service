package com.sky.model;

public class Member {

    private enum Bouquet {
        PREMIUM,
        STANDARD
    }

    private long id;
    private String name;
    private Bouquet bouquet;

    public Member(){}

    public Member(long id, String name, Bouquet bouquet) {
        this.id = id;
        this.name = name;
        this.bouquet = bouquet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }
}