package com.sky.membership.reporting.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Member implements Serializable {
    long id;
    String name;
    Bouquet bouquet;

    public enum Bouquet {
        PREMIUM,
        STANDARD
    }

}