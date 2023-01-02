package com.anatkh.third_party.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OSSController {
    public static void main(String[] args) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(format);
    }
}
