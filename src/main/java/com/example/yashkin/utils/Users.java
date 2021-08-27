package com.example.yashkin.utils;

public class Users {

    public static String getFullName(String firstName, String lastName) {
        return (firstName + lastName).replaceAll(" ", "");
    }

    public static String getFullName(String name) {
        return name.replaceAll(" ", "");
    }

}
