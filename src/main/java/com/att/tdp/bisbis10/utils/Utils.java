package com.att.tdp.bisbis10.utils;

public class Utils {
    public static String toTitleCase(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder titleCase = new StringBuilder();
        String firstLetter = s.substring(0, 1).toUpperCase();
        String restOfWord = s.substring(1).toLowerCase();
        titleCase.append(firstLetter).append(restOfWord);

        return titleCase.toString().trim();
    }

}
