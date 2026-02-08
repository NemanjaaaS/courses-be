package com.courses.models.keys;

import lombok.Data;

import java.io.Serializable;

@Data
public class EnrolledTestKey implements Serializable {

    private int test;
    private int user;

}
